##### MVC

- 解决的问题
  解决**程序模块化**问题，MVC解耦
  
- 角色划分
  • Model 模型：负责数据的加载和存储
  • View 视图：负责界面的展示
  • Controller 控制器：负责逻辑控制
  
- 数据流向
  `View` 产生事件，通知 `Controller` 进行逻辑处理，之后通知给 `Model` 更新数据，更新完成后，再将数据通知给 `View` 去更新界面
  
- 关系图
  ![MVC](https://raw.githubusercontent.com/aiybybz/ImageBad/master/MVC_20200909163051.png)
  
  