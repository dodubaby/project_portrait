# project_portrait
项目画像（分析）工具


## ProjectPortrait-原始数据采集系统
通过文本分析、语义分析，对项目源码进行整体数据采集。相关采集数据统一入库，供二级系统调用

【技术方案：Python】


## ProjectPortraitServer-数据整合系统及输出能力提供者
数据服务api提供者，对原始数据进行多维度处理和整合，二级系统可通过指定api，获取期望数据集

【技术方案：Java、Mybatis、Spring等】


## ProjectPortraitConsole-系统控制台、数据展示门户
获取数据整合系统提供的数据，并通过可视化展示方案进行展示处理，同时支持通过页面操作进行系统控制

【技术方案：大前端框架Vue、图表绘制D3.js、网络框架axios等】


## Doc
相关文档说明、附件
