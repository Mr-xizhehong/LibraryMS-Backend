-- 创建 ENUM 类型
CREATE TYPE user_role AS ENUM ('READER', 'LIBRARIAN', 'ADMIN');
CREATE TYPE user_status AS ENUM ('UNVERIFIED', 'ACTIVATED', 'BANNED');

CREATE TABLE "users" (
    uuid UUID PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    role user_role NOT NULL DEFAULT 'READER',
    status user_status NOT NULL DEFAULT 'UNVERIFIED'
)