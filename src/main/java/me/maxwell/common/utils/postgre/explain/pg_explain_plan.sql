CREATE TABLE `pg_explain_plan` (
                                   `uuid` char(32) NOT NULL,
                                   `sql_name` varchar(255) NOT NULL,
                                   `memory_used` int(9) DEFAULT NULL,
                                   `memory_wanted` int(9) DEFAULT NULL,
                                   `execution_time` decimal(10,2) DEFAULT NULL,
                                   `planning_time` decimal(10,2) DEFAULT NULL,
                                   `state` smallint(4) DEFAULT NULL,
                                   `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                                   PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `pg_explain_plan_note` (
                                        `uuid` char(32) NOT NULL,
                                        `parent_uuid` char(32) DEFAULT NULL,
                                        `plan_uuid` char(32) NOT NULL,
                                        `self_execution_time` decimal(10,2) DEFAULT NULL,
                                        `note_type` varchar(64) DEFAULT NULL,
                                        `parent_relationship` varchar(64) DEFAULT NULL,
                                        `strategy` varchar(64) DEFAULT NULL,
                                        `slice` int(8) DEFAULT NULL,
                                        `segments` int(8) DEFAULT NULL,
                                        `alias` varchar(64) DEFAULT NULL,
                                        `relation` varchar(64) DEFAULT NULL,
                                        `relation_name` varchar(64) DEFAULT NULL,
                                        `gang_type` varchar(64) DEFAULT NULL,
                                        `join_type` varchar(64) DEFAULT NULL,
                                        `startup_cost` decimal(10,2) DEFAULT NULL,
                                        `total_cost` decimal(10,2) DEFAULT NULL,
                                        `plan_rows` int(10) DEFAULT NULL,
                                        `plan_width` int(6) DEFAULT NULL,
                                        `actual_startup_time` decimal(10,2) DEFAULT NULL,
                                        `actual_total_time` decimal(10,2) DEFAULT NULL,
                                        `actual_rows` int(10) DEFAULT NULL,
                                        `actual_loops` int(8) DEFAULT NULL,
                                        `share_id` varchar(32) DEFAULT NULL,
                                        `slice_id` varchar(32) DEFAULT NULL,
                                        `filter` varchar(64) DEFAULT NULL,
                                        `join_filter` varchar(64) DEFAULT NULL,
                                        `rows_removed_by_join_filter` int(10) DEFAULT NULL,
                                        `rows_removed_by_filter` int(10) DEFAULT NULL,
                                        `senders` int(6) DEFAULT NULL,
                                        `receivers` int(6) DEFAULT NULL,
                                        `sort_key` varchar(64) DEFAULT NULL,
                                        `sort_method` varchar(64) DEFAULT NULL,
                                        `sort_space_used` int(10) DEFAULT NULL,
                                        `sort_space_type` varchar(64) DEFAULT NULL,
                                        `merge_key` varchar(255) DEFAULT NULL,
                                        `group_key` varchar(255) DEFAULT NULL,
                                        `hash_key` varchar(255) DEFAULT NULL,
                                        `hash_cond` varchar(500) DEFAULT NULL,
                                        `extra_text` varchar(8192) DEFAULT NULL,
                                        `create_time` datetime DEFAULT NULL,
                                        `state` smallint(4) DEFAULT NULL,
                                        PRIMARY KEY (`uuid`),
                                        KEY `idx_p_uuid` (`parent_uuid`),
                                        KEY `idx_node_type` (`plan_uuid`,`note_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



