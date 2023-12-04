IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[fechamento]') AND type in (N'P', N'PC'))
BEGIN
EXEC('
        CREATE PROCEDURE dbo.fechamento
            @DataRelatorio VARCHAR(10) = NULL
        AS
        BEGIN
            SET NOCOUNT ON;

            SELECT
                ''Açaís'' AS categoria,
                SUM(pi.valor_venda) AS totalCategoria
            FROM
                Pedido p
                JOIN pedido_item pi ON p.id = pi.pedido_id
                JOIN Item i ON pi.item_id = i.id
            WHERE
                (@DataRelatorio IS NULL OR (p.data >= @DataRelatorio AND p.data < DATEADD(DAY, 1, @DataRelatorio)))
                AND i.filtro = ''ACAIS''

            UNION ALL

            SELECT
                ''Para'' AS categoria,
                SUM(pi.valor_venda) AS totalCategoria
            FROM
                Pedido p
                JOIN pedido_item pi ON p.id = pi.pedido_id
                JOIN Item i ON pi.item_id = i.id
            WHERE
                (@DataRelatorio IS NULL OR (p.data >= @DataRelatorio AND p.data < DATEADD(DAY, 1, @DataRelatorio)))
                AND i.filtro = ''PARA''

            UNION ALL

            SELECT
                ''Bebidas'' AS categoria,
                SUM(pi.valor_venda) AS totalCategoria
            FROM
                Pedido p
                JOIN pedido_item pi ON p.id = pi.pedido_id
                JOIN Item i ON pi.item_id = i.id
            WHERE
                (@DataRelatorio IS NULL OR (p.data >= @DataRelatorio AND p.data < DATEADD(DAY, 1, @DataRelatorio)))
                AND i.filtro = ''BEBIDAS''

            UNION ALL

            SELECT
                ''Total Geral'' AS categoria,
                SUM(pi.valor_venda) AS TotalGeral
            FROM
                Pedido p
                JOIN pedido_item pi ON p.id = pi.pedido_id
                JOIN Item i ON pi.item_id = i.id
            WHERE
                (@DataRelatorio IS NULL OR (p.data >= @DataRelatorio AND p.data < DATEADD(DAY, 1, @DataRelatorio)))
        END'
    )
END;