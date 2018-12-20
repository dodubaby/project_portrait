# encoding: utf-8
# !/usr/bin/python3

import traceback

import dbOperate as db
import util.timeUtil as timeUtil

"""
保存文件内容-单条
"""


def saveFileData(type, full_name, path, name, suffix, class_full_name, line_count, size):
    try:
        conn = db.connect()
        cursor = conn.cursor()
        sql = 'INSERT INTO file(type, full_name, path, name, suffix, class_full_name, line_count, size) values(%s,%s,%s,%s,%s,%s,%s,%s)'
        value = [type, full_name, path, name, suffix, class_full_name, line_count, size]
        cursor.execute(sql, value)
        conn.commit()
    except:
        traceback.print_exc()
        # 发生错误时会滚
        conn.rollback()
    finally:
        # 关闭游标连接
        cursor.close()
        # 关闭数据库连接
        conn.close()


"""
保存资源内容-单条
"""


def saveResourceData(resource_type, resource_key, resource_value, file_id, file_full_name):
    try:
        conn = db.connect()
        cursor = conn.cursor()
        sql = 'INSERT INTO resource(resource_type, resource_key, resource_value, file_id, file_full_name) values(%s,%s,%s,%s,%s)'
        value = [resource_type, resource_key, resource_value, file_id, file_full_name]
        cursor.execute(sql, value)
        conn.commit()
    except:
        traceback.print_exc()
        # 发生错误时会滚
        conn.rollback()
    finally:
        # 关闭游标连接
        cursor.close()
        # 关闭数据库连接
        conn.close()


"""
保存引用关系-Java-单条
"""


def saveReferenceForJava(file_id, reference_data, reference_line):
    try:
        conn = db.connect()
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
        conn.commit()
    except:
        traceback.print_exc()
        # 发生错误时会滚
        conn.rollback()
    finally:
        # 关闭游标连接
        cursor.close()
        # 关闭数据库连接
        conn.close()


"""
保存引用关系-Resource-单条
"""


def saveReferenceForResource(file_id, reference_data, reference_line):
    try:
        conn = db.connect()
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
        conn.commit()
    except:
        traceback.print_exc()
        # 发生错误时会滚
        conn.rollback()
    finally:
        # 关闭游标连接
        cursor.close()
        # 关闭数据库连接
        conn.close()


"""
保存目标数据-单条
"""


def saveTargetData(file_id, target_data, target_data_line):
    try:
        conn = db.connect()
        cursor = conn.cursor()
        sql = 'INSERT INTO target_data(file_id, target_data, target_data_line) VALUES(%s, %s, %s)'
        value = [file_id, target_data, target_data_line]
        cursor.execute(sql, value)
        conn.commit()
    except:
        traceback.print_exc()
        # 发生错误时会滚
        conn.rollback()
    finally:
        # 关闭游标连接
        cursor.close()
        # 关闭数据库连接
        conn.close()


"""
读取资源文件列表
"""


def readResourceFileList():
    try:
        conn = db.connect()
        cursor = conn.cursor()
        sql = 'SELECT id,full_name FROM file WHERE name in (%s,%s)'
        value = ['colors.xml', 'strings.xml']
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
        # 关闭数据库连接
        conn.close()


"""
读取需要分析的文件列表
"""


def readNeedAnalysisFileList(count):
    try:
        conn = db.connect()
        cursor = conn.cursor()
        sql = 'SELECT id,full_name FROM file WHERE type = "file" AND suffix IN ("java", "xml")'
        if (count > 0):
            sql = sql + ' LIMIT ' + str(count)
        value = []
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
        # 关闭数据库连接
        conn.close()


def test():
    time = timeUtil.timeStart()
    # saveFileData("1", "2", "333", "1", "2", "333", "333")
    # readResourceFileList()
    # readAllFileList(20)
    timeUtil.timeEnd(time)


test()
