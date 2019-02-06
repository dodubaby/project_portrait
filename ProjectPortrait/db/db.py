# encoding: utf-8
# !/usr/bin/python3
import traceback

import const as const
import util.configUtil as configUtil

"""
初始化基础数据
"""


def initBaseData():
    try:
        conn = const.dbconnect
        cursor = conn.cursor()
        # 从配置文件获取基础数据sql脚本
        sqlForTag = configUtil.getConfig("./config/dbBaseData.ini", 1, 'sqlForTag')
        sqlForRule = configUtil.getConfig("./config/dbBaseData.ini", 1, 'sqlForRule')
        cursor.execute(sqlForTag)
        cursor.execute(sqlForRule)
    except:
        traceback.print_exc()
        # 发生错误时会滚
        conn.rollback()
    finally:
        # 关闭游标连接
        cursor.close()


"""
保存文件内容-单条
"""


def saveFileData(type, full_name, path, name, suffix, class_full_name, line_count, size):
    try:
        conn = const.dbconnect
        cursor = conn.cursor()
        if const.isFullAnalysis:
            sql = 'INSERT INTO file(type, full_name, path, name, suffix, class_full_name, line_count, size,is_new,scan_time) ' \
                  'values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)' \
                  'on duplicate key update line_count = %s, size = %s,is_new = %s,scan_time = %s'
            value = [type, full_name, path, name, suffix, class_full_name, line_count, size, 1, const.scanTime,
                     line_count, size,
                     0, const.scanTime]
        else:
            sql = 'INSERT INTO file(type, full_name, path, name, suffix, class_full_name, line_count, size,is_new,scan_time) ' \
                  'values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)' \
                  'on duplicate key update line_count = %s, size = %s,scan_time = %s'
            value = [type, full_name, path, name, suffix, class_full_name, line_count, size, 1, const.scanTime,
                     line_count, size,
                     const.scanTime]
        cursor.execute(sql, value)
    except:
        traceback.print_exc()
        # 发生错误时会滚
        conn.rollback()
    finally:
        # 关闭游标连接
        cursor.close()


"""
保存资源内容-单条
"""


def saveResourceData(resource_type, resource_key, resource_value, file_id, file_full_name):
    try:
        conn = const.dbconnect
        cursor = conn.cursor()
        sql = 'INSERT INTO resource(resource_type, resource_key, resource_value, file_id, file_full_name) values(%s,%s,%s,%s,%s)'
        value = [resource_type, resource_key, resource_value, file_id, file_full_name]
        cursor.execute(sql, value)
    except:
        traceback.print_exc()
        # 发生错误时会滚
        conn.rollback()
    finally:
        # 关闭游标连接
        cursor.close()


"""
保存引用关系-Java-单条
"""


def saveReferenceForJava(file_id, reference_data, reference_line):
    try:
        conn = const.dbconnect
        cursor = conn.cursor()
        sql = 'INSERT INTO reference(file_id, reference_data_id, reference_data_type, reference_data, reference_line) VALUES(' \
              '%s,' \
              '(SELECT id FROM file WHERE class_full_name = %s limit 1),' \
              '%s,' \
              '%s,' \
              '%s' \
              ')'
        value = [file_id, reference_data, "java", reference_data, reference_line]
        cursor.execute(sql, value)
    except:
        traceback.print_exc()
        # 发生错误时会滚
        conn.rollback()
    finally:
        # 关闭游标连接
        cursor.close()


"""
保存引用关系-Resource-单条
"""


def saveReferenceForResource(file_id, reference_data, reference_line):
    try:
        conn = const.dbconnect
        cursor = conn.cursor()
        sql = 'INSERT INTO reference(file_id, reference_data_id, reference_data_type, reference_data, reference_line) VALUES(' \
              '%s,' \
              '(SELECT id FROM resource WHERE resource_key = %s limit 1),' \
              '%s,' \
              '%s,' \
              '%s' \
              ')'
        reference_data_simple = reference_data.split(".")[1]  # color.aaa -> aaa
        value = [file_id, reference_data_simple, "resource", reference_data, reference_line]
        cursor.execute(sql, value)
    except:
        traceback.print_exc()
        # 发生错误时会滚
        conn.rollback()
    finally:
        # 关闭游标连接
        cursor.close()


"""
保存规则对应数据-单条
"""


def saveRuleData(rule_id, file_id, data, data_line):
    try:
        if (len(data) >= 255):
            data = data[0:200] + '...'
        conn = const.dbconnect
        cursor = conn.cursor()
        sql = 'INSERT INTO rule_data(rule_id, file_id, data, data_line) VALUES(%s, %s, %s, %s)'
        value = [rule_id, file_id, data, data_line]
        cursor.execute(sql, value)
    except:
        traceback.print_exc()
        # 发生错误时会滚
        conn.rollback()
    finally:
        # 关闭游标连接
        cursor.close()


"""
读取资源文件列表
"""


def readResourceFileList():
    try:
        conn = const.dbconnect
        cursor = conn.cursor()
        sql = 'SELECT id,full_name,scan_time FROM file WHERE name in (%s,%s) and scan_time = %s'
        value = ['colors.xml', 'strings.xml', const.scanTime]
        cursor.execute(sql, value)
        resultList = cursor.fetchall()
        for result in resultList:
            print str(result[0]) + " | " + result[1]
        return resultList
    except:
        traceback.print_exc()
        # 发生错误时会滚
        conn.rollback()
    finally:
        # 关闭游标连接
        cursor.close()


"""
读取需要分析的文件列表
"""


def readNeedAnalysisFileList(count):
    try:
        conn = const.dbconnect
        cursor = conn.cursor()
        sql = 'SELECT id,full_name FROM file WHERE type = "file" AND suffix IN ("java", "xml") AND scan_time = %s'
        if (count > 0):
            sql = sql + ' LIMIT ' + str(count)
        value = [const.scanTime]
        cursor.execute(sql, value)
        resultList = cursor.fetchall()
        # for result in resultList:
        #     print str(result[0]) + " | " + result[1]
        return resultList
    except:
        traceback.print_exc()
        # 发生错误时会滚
        conn.rollback()
    finally:
        # 关闭游标连接
        cursor.close()


"""
读取规则列表
"""


def readRuleList():
    try:
        conn = const.dbconnect
        cursor = conn.cursor()
        sql = 'SELECT * FROM rule'
        value = []
        cursor.execute(sql, value)
        resultList = cursor.fetchall()
        # for result in resultList:
        #     print str(result[0]) + " | " + result[1]
        return resultList
    except:
        traceback.print_exc()
        # 发生错误时会滚
        conn.rollback()
    finally:
        # 关闭游标连接
        cursor.close()
