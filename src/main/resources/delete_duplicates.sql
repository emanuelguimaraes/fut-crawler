DELETE FROM tb_cartao a
USING (
  SELECT MIN(ctid) as ctid, id_resource
    FROM tb_cartao
    GROUP BY id_resource HAVING COUNT(1) > 1
) b
WHERE a.id_resource = b.id_resource
AND a.ctid <> b.ctid;