DROP VIEW VGF_QTDVENDA_GIR;

/

CREATE VIEW VGF_QTDVENDA_GIR AS
SELECT CODPROD,
       TO_NUMBER(TO_CHAR(DTNEG,'YYYY')) AS ANO,
       SUM(CASE WHEN TO_CHAR(DTNEG,'MM') = '01' THEN QTDNEG ELSE 0 END) AS QTDJAN,
       SUM(CASE WHEN TO_CHAR(DTNEG,'MM') = '02' THEN QTDNEG ELSE 0 END) AS QTDFEV,
       SUM(CASE WHEN TO_CHAR(DTNEG,'MM') = '03' THEN QTDNEG ELSE 0 END) AS QTDMAR,
       SUM(CASE WHEN TO_CHAR(DTNEG,'MM') = '04' THEN QTDNEG ELSE 0 END) AS QTDABR,
       SUM(CASE WHEN TO_CHAR(DTNEG,'MM') = '05' THEN QTDNEG ELSE 0 END) AS QTDMAI,
       SUM(CASE WHEN TO_CHAR(DTNEG,'MM') = '06' THEN QTDNEG ELSE 0 END) AS QTDJUN,
       SUM(CASE WHEN TO_CHAR(DTNEG,'MM') = '07' THEN QTDNEG ELSE 0 END) AS QTDJUL,
       SUM(CASE WHEN TO_CHAR(DTNEG,'MM') = '08' THEN QTDNEG ELSE 0 END) AS QTDAGO,
       SUM(CASE WHEN TO_CHAR(DTNEG,'MM') = '09' THEN QTDNEG ELSE 0 END) AS QTDSET,
       SUM(CASE WHEN TO_CHAR(DTNEG,'MM') = '10' THEN QTDNEG ELSE 0 END) AS QTDOUT,
       SUM(CASE WHEN TO_CHAR(DTNEG,'MM') = '11' THEN QTDNEG ELSE 0 END) AS QTDNOV,
       SUM(CASE WHEN TO_CHAR(DTNEG,'MM') = '12' THEN QTDNEG ELSE 0 END) AS QTDDEZ
FROM TGFGIR1 GROUP BY TO_CHAR(DTNEG,'YYYY'),CODPROD;

/

DROP VIEW VGF_MEDVENDAS;

/

CREATE VIEW VGF_MEDVENDAS AS
  SELECT "CODPROD", "ANOMES", "MES", "VALOR", "MEDIA"
  FROM (SELECT CODPROD,
               '01/' || MED.ANO AS ANOMES,
               '01'             AS MES,
               SUM(MED.QTDJAN)  AS VALOR,
               SUM(CASE
                     WHEN MED.TIPOMEDIA = 'A' THEN MED.MEDIAAJUSTADA
                     ELSE MED.MEDIACALCULADA
                       END)     AS MEDIA
        FROM AD_BHGIRMEDVENDA MED
        GROUP BY CODPROD,
                 '01/' || MED.ANO
        UNION ALL
        SELECT CODPROD,
               '02/' || MED.ANO AS ANOMES,
               '02'             AS MES,
               SUM(MED.QTDFEV)  AS VALOR,
               SUM(CASE
                     WHEN MED.TIPOMEDIA = 'A' THEN MED.MEDIAAJUSTADA
                     ELSE MED.MEDIACALCULADA
                       END)     AS MEDIA
        FROM AD_BHGIRMEDVENDA MED
        GROUP BY CODPROD,
                 '02/' || MED.ANO
        UNION ALL
        SELECT CODPROD,
               '03/' || MED.ANO AS ANOMES,
               '03'             AS MES,
               SUM(MED.QTDMAR)  AS VALOR,
               SUM(CASE
                     WHEN MED.TIPOMEDIA = 'A' THEN MED.MEDIAAJUSTADA
                     ELSE MED.MEDIACALCULADA
                       END)     AS MEDIA
        FROM AD_BHGIRMEDVENDA MED
        GROUP BY CODPROD,
                 '03/' || MED.ANO
        UNION ALL
        SELECT CODPROD,
               '04/' || MED.ANO AS ANOMES,
               '04'             AS MES,
               SUM(MED.QTDABR)  AS VALOR,
               SUM(CASE
                     WHEN MED.TIPOMEDIA = 'A' THEN MED.MEDIAAJUSTADA
                     ELSE MED.MEDIACALCULADA
                       END)     AS MEDIA
        FROM AD_BHGIRMEDVENDA MED
        GROUP BY CODPROD,
                 '04/' || MED.ANO
        UNION ALL
        SELECT CODPROD,
               '05/' || MED.ANO AS ANOMES,
               '05'             AS MES,
               SUM(MED.QTDMAI)  AS VALOR,
               SUM(CASE
                     WHEN MED.TIPOMEDIA = 'A' THEN MED.MEDIAAJUSTADA
                     ELSE MED.MEDIACALCULADA
                       END)     AS MEDIA
        FROM AD_BHGIRMEDVENDA MED
        GROUP BY CODPROD,
                 '05/' || MED.ANO
        UNION ALL
        SELECT CODPROD,
               '06/' || MED.ANO AS ANOMES,
               '06'             AS MES,
               SUM(MED.QTDJUN)  AS VALOR,
               SUM(CASE
                     WHEN MED.TIPOMEDIA = 'A' THEN MED.MEDIAAJUSTADA
                     ELSE MED.MEDIACALCULADA
                       END)     AS MEDIA
        FROM AD_BHGIRMEDVENDA MED
        GROUP BY CODPROD,
                 '06/' || MED.ANO
        UNION ALL
        SELECT CODPROD,
               '07/' || MED.ANO AS ANOMES,
               '07'             AS MES,
               SUM(MED.QTDJUL)  AS VALOR,
               SUM(CASE
                     WHEN MED.TIPOMEDIA = 'A' THEN MED.MEDIAAJUSTADA
                     ELSE MED.MEDIACALCULADA
                       END)     AS MEDIA
        FROM AD_BHGIRMEDVENDA MED
        GROUP BY CODPROD,
                 '07/' || MED.ANO
        UNION ALL
        SELECT CODPROD,
               '08/' || MED.ANO AS ANOMES,
               '08'             AS MES,
               SUM(MED.QTDAGO)  AS VALOR,
               SUM(CASE
                     WHEN MED.TIPOMEDIA = 'A' THEN MED.MEDIAAJUSTADA
                     ELSE MED.MEDIACALCULADA
                       END)     AS MEDIA
        FROM AD_BHGIRMEDVENDA MED
        GROUP BY CODPROD,
                 '08/' || MED.ANO
        UNION ALL
        SELECT CODPROD,
               '09/' || MED.ANO AS ANOMES,
               '09'             AS MES,
               SUM(MED.QTDSET)  AS VALOR,
               SUM(CASE
                     WHEN MED.TIPOMEDIA = 'A' THEN MED.MEDIAAJUSTADA
                     ELSE MED.MEDIACALCULADA
                       END)     AS MEDIA
        FROM AD_BHGIRMEDVENDA MED
        GROUP BY CODPROD,
                 '09/' || MED.ANO
        UNION ALL
        SELECT CODPROD,
               '10/' || MED.ANO AS ANOMES,
               '10'             AS MES,
               SUM(MED.QTDOUT)  AS VALOR,
               SUM(CASE
                     WHEN MED.TIPOMEDIA = 'A' THEN MED.MEDIAAJUSTADA
                     ELSE MED.MEDIACALCULADA
                       END)     AS MEDIA
        FROM AD_BHGIRMEDVENDA MED
        GROUP BY CODPROD,
                 '10/' || MED.ANO
        UNION ALL
        SELECT CODPROD,
               '11/' || MED.ANO AS ANOMES,
               '11'             AS MES,
               SUM(MED.QTDNOV)  AS VALOR,
               SUM(CASE
                     WHEN MED.TIPOMEDIA = 'A' THEN MED.MEDIAAJUSTADA
                     ELSE MED.MEDIACALCULADA
                       END)     AS MEDIA
        FROM AD_BHGIRMEDVENDA MED
        GROUP BY CODPROD,
                 '11/' || MED.ANO
        UNION ALL
        SELECT CODPROD,
               '12/' || MED.ANO AS ANOMES,
               '12'             AS MES,
               SUM(MED.QTDDEZ)  AS VALOR,
               SUM(CASE
                     WHEN MED.TIPOMEDIA = 'A' THEN MED.MEDIAAJUSTADA
                     ELSE MED.MEDIACALCULADA
                       END)     AS MEDIA
        FROM AD_BHGIRMEDVENDA MED
        GROUP BY CODPROD,
                 '12/' || MED.ANO) VGF_MEDVENDAS