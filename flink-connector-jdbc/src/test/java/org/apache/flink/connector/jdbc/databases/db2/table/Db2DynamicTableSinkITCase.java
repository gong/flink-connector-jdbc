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

package org.apache.flink.connector.jdbc.databases.db2.table;

import org.apache.flink.connector.jdbc.databases.db2.Db2TestBase;
import org.apache.flink.connector.jdbc.databases.db2.dialect.Db2Dialect;
import org.apache.flink.connector.jdbc.table.JdbcDynamicTableSinkITCase;
import org.apache.flink.connector.jdbc.testutils.tables.TableRow;
import org.apache.flink.table.api.DataTypes;

import static org.apache.flink.connector.jdbc.testutils.tables.TableBuilder.dbType;
import static org.apache.flink.connector.jdbc.testutils.tables.TableBuilder.field;
import static org.apache.flink.connector.jdbc.testutils.tables.TableBuilder.pkField;
import static org.apache.flink.connector.jdbc.testutils.tables.TableBuilder.tableRow;

/** The Table Sink ITCase for {@link Db2Dialect}. */
class Db2DynamicTableSinkITCase extends JdbcDynamicTableSinkITCase implements Db2TestBase {
    @Override
    protected TableRow createUpsertOutputTable() {
        return tableRow(
                "dynamicSinkForUpsert",
                pkField("cnt", dbType("BIGINT NOT NULL DEFAULT 0"), DataTypes.BIGINT().notNull()),
                field("lencnt", dbType("BIGINT NOT NULL DEFAULT 0"), DataTypes.BIGINT().notNull()),
                pkField("cTag", dbType("BIGINT DEFAULT 0 NOT NULL"), DataTypes.INT().notNull()),
                field("ts", dbType("TIMESTAMP"), DataTypes.TIMESTAMP()));
    }

    @Override
    protected TableRow createCheckpointOutputTable() {
        return tableRow(
                "checkpointTable",
                field("id", dbType("BIGINT DEFAULT 0 NOT NULL"), DataTypes.BIGINT().notNull()));
    }
}
