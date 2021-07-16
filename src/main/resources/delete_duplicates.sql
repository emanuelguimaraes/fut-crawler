DELETE FROM tb_cartao a
USING (
  SELECT MIN(ctid) as ctid, id_resource
    FROM tb_cartao
    GROUP BY id_resource HAVING COUNT(1) > 1
) b
WHERE a.id_resource = b.id_resource
AND a.ctid <> b.ctid;

delete from tb_preco
where id_cartao in (
select a.id from tb_cartao a, (
  SELECT MIN(ctid) as ctid, id_resource
    FROM tb_cartao
    GROUP BY id_resource HAVING COUNT(1) > 1
) b
WHERE a.id_resource = b.id_resource
AND a.ctid <> b.ctid
);
