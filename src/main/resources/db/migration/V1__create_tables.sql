CREATE TABLE users (
                       id VARCHAR(255) PRIMARY KEY,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE payments (
                          id VARCHAR(255) PRIMARY KEY,
                          psp_type VARCHAR(50) NOT NULL,
                          psp_payment_id VARCHAR(255) NOT NULL,
                          amount INT,
                          creation_instant TIMESTAMP NOT NULL,
                          last_psp_verification_instant TIMESTAMP,
                          verification_attempt_nb INT NOT NULL,
                          verification_status VARCHAR(20) NOT NULL,
                          payer_email VARCHAR(255) NOT NULL REFERENCES users(email),
                          UNIQUE(psp_payment_id, psp_type)
);

CREATE TABLE transactions (
                              id SERIAL PRIMARY KEY,
                              nature VARCHAR(20) NOT NULL,
                              user_id VARCHAR(255) NOT NULL REFERENCES users(id),
                              payment_id VARCHAR(255) REFERENCES payments(id),
                              amount INT NOT NULL,
                              description TEXT,
                              transaction_instant TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);