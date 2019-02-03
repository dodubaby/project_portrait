# encoding: utf-8
# !/usr/bin/python3

import regular.regular as regular
from db import db
from fileAndDataCheck import ignoreReferenceData

"""
解析引用关系
"""


def analysisReference(fileId, lineContent, lineNum):
    if (lineContent.strip() == ""):
        return

    # Java引用Java
    referenceData = regular.regular(lineContent, "import ", ";")
    if (ignoreReferenceData(referenceData)):  # 忽略部分
        return
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
