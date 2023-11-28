IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[balanceamento]') AND type in (N'P', N'PC'))
    BEGIN
        EXEC('
    CREATE PROCEDURE Balanceamento
        @filter NVARCHAR(255) = NULL,
        @page INT = 0,
        @size INT = 15
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
            SUM(CASE WHEN me.tipo = ''SAIDA'' THEN (preco_venda - preco_compra) ELSE 0 END) AS lucro
        FROM movimentacao_estoque me
        LEFT JOIN item i ON i.id = me.item_id
        WHERE (@filter IS NULL OR LOWER(i.descricao) LIKE LOWER(CONCAT(''%'', @filter, ''%'')))
        GROUP BY CONVERT(VARCHAR, me.data, 23), i.descricao, me.tipo
        ORDER BY data OFFSET @page * @size ROWS FETCH NEXT @size ROWS ONLY;
    END')
    END;