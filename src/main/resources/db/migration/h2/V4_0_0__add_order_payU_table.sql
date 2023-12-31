CREATE TABLE ORDERS_PAYU  (
                        ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                        UID UUID,
                        STATUS_CODE VARCHAR(500),
                        REDIRECT_URI VARCHAR(1000),
                        ORDER_ID VARCHAR(200),
                        EXT_ORDER_ID VARCHAR(200),
                        CREATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        UPDATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);