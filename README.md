# ShoppingMall

##  基于SpringBoot+SSM+Vue移动端商城

* 项目技术栈： 
  * 后端：SpringBoot 、SpringMVC、Spring5 、 JWT 、Guava 
  * 前端：Vue 、Vuex 、Vue-router、Axios 、CubeUI 
  * 项目部署：阿里云ECSCentOS7 、 Nginx 
  * 开发环境及工具：JDK1.8、MySQL8.0、Maven、IDEA、VSCODE 、Jmeter
* 项目地址：http://112.124.18.163/
* GitHub地址
  * 前端：https://github.com/MacrossGithub-coder/ShoppingMallFront-Vue
  * 后端：https://github.com/MacrossGithub-coder/ShoppingMall

* 项目介绍：基于SpringBoot+SSM框架的前后端分离项目，v1版本功能包括用户注册、登录、用户个人信息查询、商城首页轮播图及商品介绍、查看商品详情、加入购物车、下单、清空购物车、查看订单等。

#### V1（商城基本功能）：

* 基本功能——后端api

  用户注册 http://localhost:8888/api/v1/pri/user/register

  用户登录 http://localhost:8888/api/v1/pri/user/login

  用户个人信息查询 http://localhost:8888/api/v1/pri/user/find_by_token

  首页轮播图 http://localhost:8888/api/v1/pub/list/home_banner

  首页商品信息http://localhost:8888/api/v1/pub/list/home_Commodity

  查看商品详情http://localhost:8888/api/v1/pub/list/find_commodity_detail?commodity_id=11

  加入购物车http://localhost:8888/api/v1/pri/cart/add_to_cart?commodity_id=3

  下单http://localhost:8888/api/v1/pri/order/commodity_order?commodity_id=5

  清空购物车http://localhost:8888/api/v1/pri/cart/empty_cart

  查看购物车http://localhost:8888/api/v1/pri/cart/find_user_cart_info

  查询订单http://localhost:8888/api/v1/pri/order/find_user_order

#### V1.1

* 完善业务

  - 新增JWT+Redis实现单点登录+权限鉴定（可以自定义单点登录或允许几台设备同时登录）

  * 注销：完善注销业务。

