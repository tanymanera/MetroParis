SELECT con_1.id_stazP, con_2.id_stazA, COUNT(*)
FROM connessione AS con_1 JOIN connessione AS con_2
WHERE con_1.id_stazP=con_2.id_stazP AND con_1.id_stazA=con_2.id_stazA AND 
con_1.id_connessione!=con_2.id_connessione
GROUP BY con_1.id_stazP, con_2.id_stazA;