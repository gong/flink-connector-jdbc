/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.connector.jdbc.databases.oceanbase.dialect;

import org.apache.flink.connector.jdbc.dialect.JdbcDialectTypeTest;

import java.util.Arrays;
import java.util.List;

/** The OceanBase MySql mode params for {@link JdbcDialectTypeTest}. */
class OceanBaseMysqlDialectTypeTest extends JdbcDialectTypeTest {

    public OceanBaseMysqlDialectTypeTest() {
        ddlFormat =
                "CREATE TABLE T (f0 %s)"
                        + " WITH ("
                        + "  'connector'='jdbc',"
                        + "  'url'='jdbc:%s:memory:test',"
                        + "  'table-name'='myTable',"
                        + "  'compatible-mode'='mysql'"
                        + ")";
    }

    @Override
    protected String testDialect() {
        return "oceanbase";
    }

    @Override
    protected List<TestItem> testData() {
        return Arrays.asList(
                createTestItem("CHAR"),
                createTestItem("VARCHAR"),
                createTestItem("BOOLEAN"),
                createTestItem("TINYINT"),
                createTestItem("SMALLINT"),
                createTestItem("INTEGER"),
                createTestItem("BIGINT"),
                createTestItem("FLOAT"),
                createTestItem("DOUBLE"),
                createTestItem("DECIMAL(10, 4)"),
                createTestItem("DECIMAL(38, 18)"),
                createTestItem("DATE"),
                createTestItem("TIME"),
                createTestItem("TIMESTAMP(3)"),
                createTestItem("TIMESTAMP WITHOUT TIME ZONE"),
                createTestItem("VARBINARY"),

                // Not valid data
                createTestItem("BINARY", "The OceanBase dialect doesn't support type: BINARY(1)."),
                createTestItem(
                        "VARBINARY(10)",
                        "The OceanBase dialect doesn't support type: VARBINARY(10)."),
                createTestItem(
                        "TIMESTAMP(9) WITHOUT TIME ZONE",
                        "The precision of field 'f0' is out of the TIMESTAMP precision range [0, 6] supported by OceanBase dialect."),
                createTestItem(
                        "TIMESTAMP_LTZ(3)",
                        "The OceanBase dialect doesn't support type: TIMESTAMP_LTZ(3)."));
    }
}
