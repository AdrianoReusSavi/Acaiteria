IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[balanceamento]') AND type in (N'P', N'PC'))
    BEGIN
        EXEC('
    CREATE PROCEDURE balanceamento
        @DataInicial VARCHAR(10) = NULL,
		@DataFinal VARCHAR(10) = NULL,
		@Tipo VARCHAR(10) = NULL
    AS
    BEGIN
        SET NOCOUNT ON;

        SELECT
            CONVERT(VARCHAR, me.data, 23) as data,
            i.descricao as nomeItem,
            me.tipo,
            SUM(CASE WHEN me.tipo = ''ENTRADA'' THEN me.quantidade_movimento ELSE 0 END) AS qtdCompras,
            SUM(CASE WHEN me.tipo = ''SAIDA'' THEN me.quantidade_movimento ELSE 0 END) AS qtdVendas,
            SUM(CASE WHEN me.tipo = ''SAIDA'' THEN me.valor ELSE 0 END) AS lucroBruto,
            SUM(CASE WHEN me.tipo = ''SAIDA'' THEN ((preco_venda - preco_compra) * me.quantidade_movimento) ELSE 0 END) AS lucro
        FROM movimentacao_estoque me
        LEFT JOIN item i ON i.id = me.item_id
        WHERE ((@DataInicial IS NULL AND @DataFinal IS NULL) OR (me.data BETWEEN @DataInicial AND @DataFinal)) AND (@Tipo IS NULL OR (@Tipo = me.tipo))
        GROUP BY CONVERT(VARCHAR, me.data, 23), i.descricao, me.tipo
    END')
    END;