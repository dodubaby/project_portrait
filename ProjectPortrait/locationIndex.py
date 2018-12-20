#!/usr/bin/python3
# coding=utf-8

import socket
import urllib.error
import urllib.parse
import urllib.request
from urllib.parse import quote
from util.fileUtil import writeFile
from const import addressKeyList


def requestLocationList(area, pageNum, addressKey):
    url = 'http://api.map.baidu.com/place/v2/search?query=' \
          + addressKey \
          + '&region=' \
          + area \
          + '&city_limit=true&output=json&scope=2&page_size=20&page_num=' \
          + pageNum \
          + '&ak=K38Pw1WpB2SxVdMkHuzDdOqbzo65gpHA'
    url = quote(url, '/:?=&@,')
    print('request send: ', url)

    try:
        response = urllib.request.urlopen(url, timeout=2)
        code = response.status
        message = response.reason
        print('response result:', code, message)
        # 服务器通信成功
        if code == 200:
            dataStr = str(response.read(), encoding='utf-8')
            # print('response get: ', dataStr[0:300])
            data = eval(dataStr)
            # 业务请求成功
            return data
        else:
            print('response failed: ', code, message)
    except urllib.error.HTTPError as e:
        print('request error: ', e.reason)
    except urllib.error.URLError as e:
        if isinstance(e.reason, socket.timeout):
            print('request error: time out')
        else:
            print('request error: ', e.reason)


def getLocationList(area, pageNum, addressKey):
    data = requestLocationList(area, pageNum, addressKey)
    if "" == data:
        print("get location error")
        return

    if 0 != data['status']:
        print('response failed: ', data['status'], data['message'])
        return

    result = data['results']
    content = ''
    print('----------------------')
    for data in result:
        print('location: ', data['name'])
        content += data['name'] + '\n'
    fileName = './data/' + area + '-' + addressKey + '.txt'
    writeFile(fileName, content, 'true')
    print('已写入本地文件：', fileName, len(result), '条数据')
    print('----------------------')


# 程序入口
pageNum = 0

area = input('请输入要查找的区域（区级单位）:\n')
code = input('请输入要查找的位置点类型：\n'
             + str(addressKeyList)
             + "\n")
addressKey = addressKeyList.get(code, '字典查询失败')

print('开始检索...')
while pageNum <= 20:
    pageNum += 1
    getLocationList(area, str(pageNum), addressKey)
print('检索完成')
