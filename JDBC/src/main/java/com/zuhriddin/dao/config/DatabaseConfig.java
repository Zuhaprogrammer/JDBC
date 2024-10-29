package com.zuhriddin.dao.config;

import java.sql.Connection;

public interface DatabaseConfig {
    Connection connect();
}
