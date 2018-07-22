#[回顾]

1.VO:Value Object:

2.<foreach collection="array" item="定义变量" open="(" 	  		separator="," close=")">
	#{变量}
  </foreach>

3.pl/sql:过程

	定义过程格式:

	create procedure pname
	begin
		sql;
	end
	
	call pname

4.消息摘要:

4.1:

4.2:md5,sha

4.3.数据完整性

4.4.特性:消息内容相同,那么消息摘要相同,反过来同样成立;

4.5.不可逆

## 消息摘要

## 密码加密

通过某种算法,将`明文`变成`密文`,保存到数据库中.

1.明文:没有经过任何处理的字符串,大家都能看懂的文字.

2.密文:经过处理的字符串,不能看懂原文的意思.

##加盐

	文本框的密码+"你喜欢编程吗?"

##学子商城中的密码处理

1.注册

2.登录

3.修改密码

## IOC

## DI

## AOP

##(AOP概念重要)

1.AOP:面向切面编程,是面向对象编程的重要组成部分.在不改变业务逻辑的基础上,对 横切逻辑 进行扩展.

2.应用场景:

	性能测试
	权限管理
	日志管理
	事务管理

3.开发步骤

1).导入jar包


	<!-- aop 依赖jar包 -->
	<dependency>
	  <groupId>org.aspectj</groupId>
	  <artifactId>aspectjweaver</artifactId>
	  <version>1.8.8</version>
	</dependency>
	
	<dependency>
	  <groupId>org.aspectj</groupId>
	  <artifactId>aspectjrt</artifactId>
	  <version>1.8.8</version>
	</dependency>
	
	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-aop</artifactId>
  		<version>4.3.9.RELEASE</version>
  	</dependency>

2)定义切面类

	@Component
	@Aspect    //表示切面类
	public class DemoAop{
		@Before("bean(userService)")
		public void test1(){
			System.out.println("之前....");
		}
	}

3)配置文件

	<!-- 配置组件扫描 -->
		<context:component-scan 
			base-package="cn.tedu.store.aop" />
		<aop:aspectj-autoproxy/>

测试:

##通知

	//@Component表示实例化对象
	//@Aspect表示当前类是切面类
	@Component
	@Aspect
	public class DemoAop {
	//@Before表示在业务层方法之前执行
	//bean()表示规范的格式
	//bean(userService)表示在userService业务层的
	//方法之前执行
	@Before("bean(userService)")
	public void test1(){
		System.out.println("之前....");
	}
	//@After最终通知,不管有没有异常都会执行的代码
	@After("bean(userService)")
	public void test2(){
		System.out.println("之后1...");
	}
	//@AfterReturning不发生异常的时候执行的代码
	@AfterReturning("bean(userService)")
	public void test3(){
		System.out.println("之后2...");
	}
	//@AfterThrowing发生异常的时候执行的代码
	@AfterThrowing("bean(userService)")
	public void test4(){
		System.out.println("发生异常...");
	}
	//环绕通知
	//1.必须要有返回值
	//2.必须有参数 ProceedingJoinPoint jp
	//3.参数对象调用方法jp.proceed();
	@Around("bean(userService)")
	public Object test5(ProceedingJoinPoint jp) 
			throws Throwable{
		System.out.println("环绕通知前....");
		Object obj = jp.proceed();//表示调用业务方法
		//login();
		System.out.println("环绕通知后....");
		
		return obj;//返回业务逻辑方法的返回值
	}
	
	}


##概念

连接点:业务逻辑方法,都叫连接点.

切点:执行切面方法的业务逻辑方法,叫切点

##切点的定义

1.基于bean对象的切点的定义(了解)

	@Around("bean(userService)||bean(addressService)")

2.基于类切点的定义(了解)

	@Around("within(cn.tedu.store.service.*Service)")

3.基于方法切点的定义

	//*表示方法的返回类型
	//cn.tedu.store.service.UserService.login表示方法名
	//(..) ..表示方法的参数列表
	@Around("execution(* cn.tedu.store.service.UserService.login(..))")

	@Component
	@Aspect
	public class TestAop1 {
	@Before("execution(* cn.tedu.store.service.UserService.login(..))")
	public void log(){
		System.out.println("基于方法的切点的定义...");
	}
	
	}

##AOP实现原理(重要)

aop是面向切面编程,spring-aop是这种思想的一个实现框架,底层使用了jdk的动态代理和cglib代理方式实现的.

jdk的动态代理特点:必须有接口

cglib代理的特点:没有接口

spring-aop 的jdk的动态代理和cglib代理自动转换.


##代理模式:使用另一种方式访问目标对象的规则.好处:不仅可以实现目标类方法的功能,还可以扩展目标类方法的功能.

##静态代理的实现

1.定义接口

	public interface IStudentService {
	void addStudent();
	void updateStudent();
	void deleteStudent();
	void getById();
	void getAll();

	}

2.定义目标类

	@Service
	public class StudentService implements 
							IStudentService{
	@Override
	public void addStudent() {
		System.out.println("StudentService.addStudent");
		
	}

	@Override
	public void updateStudent() {
		
	}

	@Override
	public void deleteStudent() {
	
	}

	@Override
	public void getById() {
		
	}

	@Override
	public void getAll() {
		
	}

	}

3.定义切面

	@Component
	public class StudentAop {
	public void log(){
		System.out.println("StudentAop.log");
	}

	}

4.定义代理类

	@Component
	public class StudentProxy implements 
							IStudentService{
	//在代理类中,既有目标类的对象,
	//还要有切面类的对象
	@Resource
	private IStudentService studentService;
	@Resource
	private StudentAop studentAop;
	@Override
	public void addStudent() {
		//前置通知
		studentAop.log();
		studentService.addStudent();
		
	}
	@Override
	public void updateStudent() {
		
	}

	@Override
	public void deleteStudent() {
	}

	@Override
	public void getById() {
	}

	@Override
	public void getAll() {
		
	}

	}

	

5.测试

	public class TestAop {
	@Test
	public void testStudentProxy(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-aop.xml",
						"application-service.xml",
						"application-dao.xml");
		IStudentService st =
				ac.getBean("studentProxy",
				IStudentService.class);
		st.addStudent();
		
	}

	}

特点:不改变 目标类的基础上,对功能进行了扩展.

缺点:

	代理类的数量会不断增加;如果接口中的方法增加了,要维护目标类还有代理类的方法.

##动态代理(有难度)

1.2.3同上.

4.动态代理类的辅助类

	//生成动态代理类的辅助类
	@Component
	public class StudentProxyHandler implements 
					InvocationHandler{
	@Resource
	private StudentService studentService;
	@Resource
	private StudentAop studentAop;
	//获取动态代理类的对象
	public Object getProxy(){
		//第一个参数:表示目标类的类加载器的对象
		//第二个参数:表示目标类的接口对象
		//第三个参数:表示实现了InvocationHandler接口的类的对象
		return Proxy.newProxyInstance(
			studentService.getClass().getClassLoader(), 
			studentService.getClass().getInterfaces(),
			this);
	}
	
	@Override
	public Object invoke(Object proxy, 
			Method method, Object[] args) 
					throws Throwable {
			//模拟前置通知
			studentAop.log();
			//表示通过反射调用studentService的方法
			//studentService.login(args);
			Object obj =
			method.invoke(studentService, args);
			
		return obj;
	}

	}

5.测试类

	@Test
	public void testStudentHandler(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-aop.xml",
						"application-service.xml",
						"application-dao.xml");
		//获取辅助类的对象
		StudentProxyHandler sph = 
		   ac.getBean("studentProxyHandler",
				StudentProxyHandler.class);
		//获取代理类的对象
		IStudentService ss = 
			(IStudentService)sph.getProxy();
		ss.addStudent();
	}

##AOP的事务处理(重要)

配置事务:

	<!-- 实例化事务的切面对象 -->
		<bean id="transactionManager" 
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
			<property name="dataSource" ref="dataSource"/>
		 </bean>
		 <!-- 注解驱动 -->
		 <tx:annotation-driven 
		 transaction-manager="transactionManager"/>

在类上添加注解:

	@Transactional
	public class AddressService implements 

	....




