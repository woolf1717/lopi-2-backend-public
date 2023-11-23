
CREATE TABLE ORDERS_PAYU  (
                        ID BIGSERIAL PRIMARY KEY,
                        UID UUID NOT NULL,
                        STATUS_CODE VARCHAR(100),
                        REDIRECT_URI VARCHAR(500),
                        ORDER_ID VARCHAR(200) NOT NULL,
                        EXT_ORDER_ID VARCHAR(200) NOT NULL,
                        CREATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        UPDATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);