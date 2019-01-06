# encoding: utf-8
# !/usr/bin/python3

import regular.regular as regular
from db import db
from util.fileUtil import *

"""
解析资源列表
"""


def analysisResourceList():
    fileList = db.readResourceFileList()
    for file in fileList:
        # 获取资源内容
        fileId = str(file[0])
        fileFullName = str(file[1])
        getResource(fileId, fileFullName)


"""
获取资源内容
"""


def getResource(fileId, fileFullName):
    print "开始解析资源文件：" + fileId + " | " + fileFullName
    iter_f = readFileContent(fileFullName)
    if (not resourceFileCheck(iter_f)):
        return
    lineNum = 0
    for line in iter_f:  # 遍历文件，一行行遍历，读取文本
        lineNum = lineNum + 1
        key = ''
        value = ''

        # 解析是否是color
        resource = regular.regularResourceColor(str(line))
        key = resource['key']
        value = resource['value']
        if (key != '' and value != ''):
            db.saveResourceData("color", key, value, fileId, fileFullName)
            print "获取到资源color： " + key + " -> " + value
            continue

        # 解析是否是string
        resource = regular.regularResourceString(str(line))
        key = resource['key']
        value = resource['value']
        if (key != '' and value != ''):
            db.saveResourceData("string", key, value, fileId, fileFullName)
            print "获取到资源string： " + key + " -> " + value
            continue

        # 解析是否是dimen
        resource = regular.regularResourceDimen(str(line))
        key = resource['key']
        value = resource['value']
        if (key != '' and value != ''):
            db.saveResourceData("dimen", key, value, fileId, fileFullName)
            print "获取到资源dimen： " + key + " -> " + value
            continue

    print "文件行数： " + str(lineNum)


"""
文件检查
"""


def resourceFileCheck(iter_f):
    # 前n行有特定标签，则认为正常
    isFileOK = False
    lineNum = 0
    for line in iter_f:  # 遍历文件，一行行遍历，读取文本
        lineNum = lineNum + 1
        if ("<resources>" in line):
            isFileOK = True
        if (4 == lineNum):
            break
    print "检查资源文件结果：" + str(isFileOK)
    return isFileOK

