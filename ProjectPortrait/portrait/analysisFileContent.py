# encoding: utf-8
# !/usr/bin/python3

import portrait.analysisFileContent4Reference as reference
import portrait.analysisFileContent4Rule as rule
from db import db
from util.fileUtil import readFileContent

"""
解析全部文件内容
"""


def analysisAllFileContent():
    print ("analysis all file content start")
    fileList = db.readNeedAnalysisFileList(5000)
    for file in fileList:
        fileId = str(file[0])
        fileFullName = str(file[1])
        suffix = str(file[2])
        analysisFileContent(fileId, fileFullName, suffix)
    print ("analysis all file content end")


"""
解析文件内容
"""


def analysisFileContent(fileId, fileFullName, suffix):
    iter_f = readFileContent(fileFullName)
    lineNum = 0
    for lineContent in iter_f:  # 遍历文件，一行行遍历，读取文本
        lineNum = lineNum + 1
        # 解析引用关系
        reference.analysisReference(fileId, lineContent, lineNum)
        # 解析规则数据
        rule.analysisRuleData(fileId, lineContent, lineNum, suffix)
