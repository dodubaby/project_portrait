# encoding: utf-8
# !/usr/bin/python3

from util.fileUtil import readFileContent
from db import db
import util.timeUtil as timeUtil
import regular.regular as regular

# 自定义数据
TARGET_DATA = 'L.jinzhu'

"""
解析文件内容
"""


def analysisAllFileContent():
    fileList = db.readNeedAnalysisFileList(2000)
    for file in fileList:
        fileId = str(file[0])
        fileFullName = str(file[1])
        analysisFileContent(fileId, fileFullName)


"""
解析文件内容
"""


def analysisFileContent(fileId, fileFullName):
    iter_f = readFileContent(fileFullName)
    lineNum = 0
    for lineContent in iter_f:  # 遍历文件，一行行遍历，读取文本
        lineNum = lineNum + 1
        # 解析引用关系
        analysisReference(fileId, lineContent, lineNum)
        # 解析自定义数据
        analysisTargetData(fileId, lineContent, lineNum)


"""
解析引用关系
"""


def analysisReference(fileId, lineContent, lineNum):
    if (lineContent.strip() == ""):
        return

    # Java引用Java
    referenceData = regular.regular(lineContent, "import ", ";")
    if (referenceData != ""):
        db.saveReferenceForJava(fileId, referenceData, lineNum)
        return

    # Java引用资源:"R.color.xxx,"、"R.color.xxx)"
    lineContent = lineContent.strip()
    lineContent = lineContent.replace(r",", ")")
    referenceData = regular.regular(lineContent.strip(), r"R\.", r"\)")
    if (referenceData != "" and "." in referenceData):
        db.saveReferenceForResource(fileId, referenceData, lineNum)
        return

    # Java引用Layout
    # TODO

    # Layout引用资源
    # TODO


"""
解析自定义数据
"""


def analysisTargetData(fileId, lineContent, lineNum):
    if (TARGET_DATA in lineContent):
        db.saveTargetData(fileId, TARGET_DATA, lineNum)


"""
test
"""


def test():
    time = timeUtil.timeStart()
    analysisAllFileContent()
    timeUtil.timeEnd(time)


test()
