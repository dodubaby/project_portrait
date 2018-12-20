# encoding: utf-8
#!/usr/bin/python3

import urllib.request
from urllib.parse import quote

url = 'http://www.baidu.com'
file = urllib.request.urlopen(url)
data = file.read().decode('utf-8')
print('----------------------')
print(data[500:1200])

print('----------------------')
print('----------------------')
print('----------------------')

url = 'http://api.map.baidu.com/place/v2/search?query=村委会&region=平谷&city_limit=true&output=json&scope=2&page_size=20&page_num=1&ak=K38Pw1WpB2SxVdMkHuzDdOqbzo65gpHA'
print(url)
url = quote(url, '/:?=&@,')
print(url)

file = urllib.request.urlopen(url)
data = file.read().decode('utf-8')
print('----------------------')
print(data[500:1200])
