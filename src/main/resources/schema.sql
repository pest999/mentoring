CREATE TABLE IF NOT EXISTS brands (
                                      BRAND_ID INT PRIMARY KEY,
                                      NAME VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS offers (
                                      OFFER_ID INT PRIMARY KEY,
                                      BRAND_ID INT,
                                      START_DATE TIMESTAMP,
                                      END_DATE TIMESTAMP,
                                      PRICE_LIST INT,
                                      PARTNUMBER VARCHAR(255),
                                      PRIORITY INT,
                                      PRICE DECIMAL(10, 2),
                                      CURR VARCHAR(10)
);