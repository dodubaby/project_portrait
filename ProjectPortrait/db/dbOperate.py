# encoding: utf-8
# !/usr/bin/python3

import traceback

import pymysql as mdb

import const as const

# 数据库名
DB_NAME = 'project_portrait'

# 数据库表名
TABLE_NAME_FILE = "file"  # 文件
TABLE_NAME_RESOURCE = "resource"  # 资源
TABLE_NAME_REFERENCE = "reference"  # 引用关系
TABLE_NAME_RULE = "rule"  # 规则
TABLE_NAME_RULE_DATA = "rule_data"  # 规则数据
TABLE_NAME_TAG = "tag"  # 标签
TABLE_NAME_TAG_DATA = "tag_data"  # 标签数据

# 数据库配置
config = {
    'host': '127.0.0.1',
    'port': 3306,
    'user': 'root',
    'passwd': '1qaz@WSX',
    'db': DB_NAME,
    'charset': 'utf8'
}

# 数据库配置（测试服务器）
# config = {
#     'host': '10.33.106.127',
#     'port': 3306,
#     'user': 'newhouse',
#     'passwd': 'newhouse',
#     'db': DB_NAME,
#     'charset': 'utf8'
# }

"""
连接数据库
"""


def connect():
    print ("db connect start")
    const.dbconnect = mdb.connect(**config)
    const.dbconnect.select_db(DB_NAME)
    # 如果使用事务引擎，可以设置自动提交事务，或者在每次操作完成后手动提交事务conn.commit()
    const.dbconnect.autocommit(True)
    print ("db connect end")


"""
断开数据库
"""


def disconnect():
    print ("db disconnect start")
    # 关闭数据库连接
    const.dbconnect.close()
    print ("db disconnect end")


"""
新建数据库
"""


def createDatabases():
    try:
        conn = const.dbconnect
        # 使用cursor()方法获取操作游标
        cursor = conn.cursor()
        # 因该模块底层其实是调用CAPI的，所以，需要先得到当前指向数据库的指针。
        cursor.execute('DROP DATABASE IF EXISTS %s' % DB_NAME)
        cursor.execute('CREATE DATABASE IF NOT EXISTS %s' % DB_NAME)
    except:
        traceback.print_exc()
        # 发生错误时会滚
        conn.rollback()
    finally:
        # 关闭游标连接
        cursor.close()


"""
新建数据表
"""


def createTables():
    try:
        print ("db create tables start")
        conn = const.dbconnect
        # 使用cursor()方法获取操作游标
        cursor = conn.cursor()

        # 创建file表
        cursor.execute('DROP TABLE IF EXISTS %s' % TABLE_NAME_FILE)
        cursor.execute('CREATE TABLE %s('
                       'id bigint unsigned zerofill NOT NULL AUTO_INCREMENT,'
                       'type varchar(255) DEFAULT NULL,'
                       'full_name varchar(190) DEFAULT NULL,'
                       'path varchar(255) DEFAULT NULL,'
                       'name varchar(255) DEFAULT NULL,'
                       'suffix varchar(255) DEFAULT NULL,'
                       'class_full_name varchar(255) DEFAULT NULL,'
                       'line_count bigint DEFAULT NULL,'
                       'size bigint DEFAULT NULL,'
                       'is_new tinyint(1) default 1,'
                       'scan_time varchar(255) DEFAULT NULL,'
                       'PRIMARY KEY (id),'
                       'UNIQUE KEY(full_name)'
                       ') ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin'
                       % TABLE_NAME_FILE)

        # 创建resource表
        cursor.execute('DROP TABLE IF EXISTS %s' % TABLE_NAME_RESOURCE)
        cursor.execute('CREATE TABLE %s('
                       'id bigint unsigned zerofill NOT NULL AUTO_INCREMENT,'
                       'resource_type varchar(255) DEFAULT NULL,'
                       'resource_key varchar(255) DEFAULT NULL,'
                       'resource_value varchar(255) DEFAULT NULL,'
                       'file_id varchar(255) DEFAULT NULL, '
                       'file_full_name varchar(255) DEFAULT NULL,'
                       'PRIMARY KEY (id)'
                       ') ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin'
                       % TABLE_NAME_RESOURCE)

        # 创建reference表
        cursor.execute('DROP TABLE IF EXISTS %s' % TABLE_NAME_REFERENCE)
        cursor.execute('CREATE TABLE %s('
                       'id bigint unsigned zerofill NOT NULL AUTO_INCREMENT,'
                       'file_id bigint DEFAULT NULL,'
                       'reference_data_id bigint DEFAULT NULL,'
                       'reference_data_type varchar(255) DEFAULT NULL, '
                       'reference_data varchar(255) DEFAULT NULL, '
                       'reference_line bigint DEFAULT NULL,'
                       'PRIMARY KEY (id)'
                       ') ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin'
                       % TABLE_NAME_REFERENCE)

        # 创建rule表
        cursor.execute('DROP TABLE IF EXISTS %s' % TABLE_NAME_RULE)
        cursor.execute('CREATE TABLE %s('
                       'id bigint unsigned zerofill NOT NULL AUTO_INCREMENT,'
                       'key_left varchar(255) DEFAULT NULL, '
                       'key_right varchar(255) DEFAULT NULL, '
                       'type varchar(40) DEFAULT NULL, '
                       'remark varchar(255) DEFAULT NULL, '
                       'creater varchar(40) DEFAULT NULL, '
                       'create_time varchar(255) DEFAULT NULL,'
                       'PRIMARY KEY (id)'
                       ') ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin'
                       % TABLE_NAME_RULE)

        # 创建rule_data表
        cursor.execute('DROP TABLE IF EXISTS %s' % TABLE_NAME_RULE_DATA)
        cursor.execute('CREATE TABLE %s('
                       'id bigint unsigned zerofill NOT NULL AUTO_INCREMENT,'
                       'rule_id bigint NOT NULL,'
                       'file_id bigint DEFAULT NULL,'
                       'data varchar(255) DEFAULT NULL, '
                       'data_line bigint DEFAULT NULL,'
                       'status varchar(20) DEFAULT NULL, '
                       'PRIMARY KEY (id)'
                       ') ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin'
                       % TABLE_NAME_RULE_DATA)

        # 创建tag表
        cursor.execute('DROP TABLE IF EXISTS %s' % TABLE_NAME_TAG)
        cursor.execute('CREATE TABLE %s('
                       'id bigint unsigned zerofill NOT NULL AUTO_INCREMENT,'
                       'type varchar(255) DEFAULT NULL, '
                       'value varchar(255) DEFAULT NULL, '
                       'PRIMARY KEY (id)'
                       ') ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin'
                       % TABLE_NAME_TAG)

        # 创建tag_data表
        cursor.execute('DROP TABLE IF EXISTS %s' % TABLE_NAME_TAG_DATA)
        cursor.execute('CREATE TABLE %s('
                       'id bigint unsigned zerofill NOT NULL AUTO_INCREMENT,'
                       'tag_id bigint DEFAULT NULL,'
                       'data_type varchar(20) DEFAULT NULL, '
                       'data_id bigint DEFAULT NULL,'
                       'PRIMARY KEY (id)'
                       ') ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin'
                       % TABLE_NAME_TAG_DATA)
        print ("db create tables end")
    except:
        traceback.print_exc()
        # 发生错误时会滚
        conn.rollback()
    finally:
        # 关闭游标连接
        cursor.close()


"""
清除表数据
"""


def clearTableData(tableName):
    try:
        print ("db clear table data start")
        conn = const.dbconnect
        # 使用cursor()方法获取操作游标
        cursor = conn.cursor()

        cursor.execute('truncate table %s' % tableName)
        print ("db clear table data end")
    except:
        traceback.print_exc()
        # 发生错误时会滚
        conn.rollback()
    finally:
        # 关闭游标连接
        cursor.close()
