package cn.edu.nwsuaf.videoCaptureServer;


/**
 * 用于在完成视频音频数据捕获后，等待客户连接请求，并获得客户力
 * @author Administrator
 *
 */
public class VCS {

	
	
	Serversoekets=newServersoeket印ort);
	try{
	Soeketsoeket=s.aceePt();
	仰{
	elient=soeket.gethietAddress();
	BufferedReaderin=
	newBufferedReader(
	newInPutStreamReader(
	soeket.gethiPutstream()));
	Printwriterout=
	newPrintwriter(
	newBufferedwriter(
	newoutPutstreamwriter(
	soeket.getoutPutstream()))，true);
	JMF平台开发B/s模式下多媒体计算机远程监控系统的研究与实现
	theViedoAddress=elient.getHostAddress();
	theAudioAddress=elient.getHostAddress();
	theViedoPort=in.readLine().trim();
	theAudioPort=in.readLine().trim();
	theTTL=in.readLine().trim():
	}finally{
	socket.elose();
	}
	}finally{
	s·close();
	}
	}
	JMF服务器在获得客户方的地址和接收视频、音频的端口以及竹L设置后，进行RTP
	通讯传输媒体流给客户方。
	4.1.2服务器方发送R丁p媒体流
	JMF服务器获得客户方信息后，调用doTransmit()函数，将捕获的实时媒体体流(视频
	和音频)传输给客户。
	在doTransmit()函数中通过通道管理器SessionManager传输RTP媒体流的关键步骤为:
	1.从捕获媒体数据时建立的处理器(Proeessor)取得数据源(natasource)。
	if(Proeessor==null)retum(false):
	realizeProeessor():
	dsoutPut=Proeessor.getDataoutPut():
	2.创建类MyhiitTrans而t的实例，等客户方的连接。
	MyhiitTransmitm林nit=newMylnitTransmit(1234):
	m扒nit.waitClieni():
	3.如果媒体流是视频格式，则根据客户方接收视频的地址、端口和TTL值创建会话
	管理器，并依据数据源，为此会话管理器创建并启动发送流(Sendstream)。
	format=arrstreamsli」.getFormat();
	if(formatinstanceofVideoFormat){
	strVideoAddress二如nit.getViedoAddress():
	strVideoPorts二外nit.getViedoPort():
	strVideoTTL劝外nit.getVideoTTL();
	managersession=JMFUtils.ereatesessionManager(strAddress，
	strPort，strTtl，null);
	streamsend=managersession.ereatesendstream(dsoutPut，i);
	streamsend.Start();
	4.如果媒体流是音频格式，则根据客户方接收视频的地址、端口和TTL值创建会话
	管理器，并依据数据源，为此会话管理器创建并启动发送流(sendstream)。
	format=arrstreams【i」.getFormat():
	if(formatinstanceofAudioFormat){
	strVideoAddress二协nit.getAudioAddress();
	strVideoPorts二丁n犯nit.getAudioPort():
	JMF平台开发B/S模式下多媒体计算机远程监控系统的研究与实现
	strVideoTTL书n外nit.getAudioTTL();
	managersession=JMFUtils.ereatesessionManager(strAddress，
	strPort，strTtl，null);
	streamsend=managersession.ereatesendstream(dsoutPut，i):
	streamsend.start():
	Sendstream启动后，开始媒体流的传输，并通过Sendstream的方法控制传送。在
	Sendstream上接收听事件注册一个Sendstre田卫Llstener。
	第二节运行在客户端浏览器中的ApPIat程序部分
	这部分APplet程序运行在客户端浏览器中，主要功能是实现与服务器的连接，在客厂’
	浏览器中实现实时监控和文件播放。
	主要的类和功能介绍如下:
	令AddrAPPlet:
	获得客户方地址、设定的视频、音频端口和TTL值，并与JMF服务器连接，将客
	户方数据传给JMF服务器。
	令RealTimeRTPPlayer:
	接收服务器发送的实时媒体数据，并在客户方浏览器中进行回放。
	个FilePlayer:
	接收服务器发送的媒体文件数据，并在客户方浏览器中进行回放。
	4.2.1实时媒体数据的接收和播放
	其中实现B/s模式的关键类运行在客户方浏览器中的RealTimeRTPPlayer，它负责实时
	媒体数据的接收和播放。类RealTimeRTPPlayer实现的关键步骤如下:
	在AddrApplet.java向服务器发出连接请求后，服务器从请求中获得客户端接收地士}}一和
	端口等信息，并发送RTP数据，运行在客户端的RealTimeRTPPlayer.java接收和处理来白服务
	器端的数据。这个applet允许客户端在一个音频和一个视频会话(session)中回放媒体流。
	具体的实现步骤如下:
	1.在applet的init()中，获得客户接收媒体数据的地址和端口。并根据用户的选择，
	startsessio咖anage:()负责启动视频或音频会话。
	address=getCodeBase().getHost();
	media=getParameter(，，video”);
	if(media.equals(，，On，，))王//如果接收视频数折{
	portstr=getParameter(，，videoport，，)://从asp程序中获得视频端
	口
	StartsessiollManager(address，//服务器方的地址
	StrTohit(Portstr)，//服务器方的端口
	，，video”);
	}
	if(media.e职als(，，On，，)){
	Portstr=getPara们neter(，，audioPort，，):
	StartsessiollManager(address，
	JMF平台开发B/S模式下多媒体计算机远程监控系统的研究与实现
	StrTolnt印ortstr)，
	，，audio”):
	}
	2.在startsessionManager()中，创建一个新的RTPsessionM『。
	SessionManagerm叨mgr=newRTPSessionMgr();
	3.将此applet注册为一个RTp会话的监听者，并调用RTPsesslonMallager的
	initsession()和startsession()方法，初始化并开始这次会话。
	mynlgr.addReeeivestreamListener(this):
	mynlgr.initsession(loealaddr，
	m叨叮gr.generateSSRC()，
	userdeselist，
	0.05，
	0.25);
	m犯ngr.st斌Session(sessaddr，l，null);
	4.UPdateO将会处理所有由以上声明的SessionManager发送的RTP事件消息。
	令如果有新的RTP数据流，创建一个新的Player对象。
	if(eventinstaneeofNewReeeivestreamEvent){
	Reeeivestreamstream=((NewReeeivestreamEvent)
	eveni).getReeeivestream();
	Datasoureedsouree=stream.getDatasource();
	newPlayer=Manager.ereatePlayer(dsource);
	}
	个如果是新建的Player，我们要在启动Player之前侦听它的事件，为它增加
	ControllerListener。
	newPlayer.addControllerListener(this);
	newPlayer.start();
	5.由eontrollerUpdate()处理pl盯er的事件。
	如果pla”r己实现，将此plaver的视觉(visual)部件加到叩plet中，实现播放。
	visualComPoneni=Player.getVisualComPonent();
	Panel.add(，，Cenier，，，visualComPonent);
	Panel.validate():
	validate();
	实现实时监控的apPlet核心代码如下:
	PublieelassRealTimeRTPPlayerextendsAPPletimPlements
	ControllerListener，ReeeivestreamListener，AetionListener盗
	publievoidinit(){
	setLayout(newBorderLayout());
	Panelbuttonl，anel=newPanel();
	buttonPanel.setLayout(newFlowLayout());
	add(，，North，，，butto廿anel):
	media=getPararneter(，，video，，);
	if(media.equals(，，On，，)){
	address=getParameter(，，videosession，，);
	JMF平台开发B/S模式下多媒体计算机远程监控系统的研究与实现
	Portstr=getParameter(，，videoPort，，):
	St斌SessionManager(address，
	StrTohit(Portstr)，
	，，video”);
	if(videomgr一null){
	System.err.Priniln(，，nullvideomanager”):
	return;
	}
	videobutton=newButton(，，VideoRTPMonitor，，);
	videoblltton.addAetionListener(this);
	buttonPanel.add(videobutton);
	}
	media=getParameter(，，audio，，);
	if(media.equals(，，On，，)){
	address=getPararneter(，，audiosession，，);
	Portstr=getParameter(，，audioPort，，):
	StartsessionManager(address，
	StrTolnt(Portstr)，
	”audio”);
	if(audiomgr一null){
	System.err.Priniin(，，nullaudiomanager，，);
	return;
	}
	audiobutton=newButton(，，AudioRTPMonitor，，);
	audiobutton.addAetionListener(this);
	buttonPanel.add(audiobutton);
	}
	}
	Publievoidstart(){
	if(videoPlayer!=null){
	videoplayer.start();
	}
	if(Playerlist=null)
	fetllm;
	for(inii=0;i<playerlist.size();i++){
	PlayerPlayer二(Player)Playerlist.elementAt(i);
	if(pl盯er!二null)
	newPlayerwindow印layer):
	}
	}
	PublievoidstoP(){
	if(videoPlayer!=null){
	videoPlayer.elose():
	}
	弓g
	JMF平台开发B/S模式下多媒体计算机远程监控系统的研究与实现
	if印layerlist=null)
	for(inii=0;i<playeriist.size();i++){
	PlayerPlayer=(Player)Playerlist.elementAt(i);
	if(Player!=null){
	Player.elose():
	}
	}
	}
	Publicvoiddestroy(){
	Stringreason=”ShutdownRTPPlayer，，
	if(videomgr!=null){
	videomgr.elosesession(reason);
	videoPlayer=null:
	videomgr=null;
	}
	if(audiomgr!=null){
	audiomgr.elosesession(reason);
	andiomgr=null:
	}
	s即er.destroy():
	}
	PublievoidactionPerformed(AetionEventevent)谧
	B毗onbutton=(Button)eveni.getsource();
	if((button=videobutton)&&(videomgr!=null))
	videogui=newPartieiPantListwindow(videomgr);
	if((button=audiobutton)&&(audiomgr!=null))
	audiogui=newPartieiPantListwindow(audiomgr);
	PublicsynehronizedvoideonirollerUPdate(ControllerEventevent){
	PlayerPlayer=null:
	Conlrollereontroller=(Co血oller)event.getsouree();
	if(eontrollerinstaneeofPlayer)
	Player=(Player)event.getsource();
	if印layer一null)
	fetllm;
	if(eveniinstaneeofRealizeComPleteEvent){
	if((visualComPoneni=
	Player.getVisualComPonent())!=null)笼
	widih=visualComPonent.getPreferredsize().width:
	1()
	JMF平台开发B/S模式下多媒体计算机远程监控系统的研究与实现
	height+=visualComPoneni.getPreferredsize().height;
	if印anel一null){
	Panel=newPanel();
	rePositionPanel(width，height);
	Panel.setLayout(newBorderLayout());
	}
	Panel.add(，，Cenier，，，visualComPonent);
	Panel.validate():
	}
	if((eontrolComP0nent=
	Player.getContr01PanelComP0neni())!=null){
	height+=eonirolComPonent.getPreferredSize().height:
	if(Panel==null){
	Panel=newPanel():
	Panel.setLayout(newBorderLayout()):
	}
	rePositionPanel(width，height);
	Panel.add(，，South，，，eontrolC0mPonent);
	Panel.validate():
	if印anel!二null){
	add(，，Center，，，Panel):
	invalidate();
	}
	}
	if(eventinstaneeofSizeChangeEvent)笼
	if(Panel!=null){
	SizeChangeEventsee=(SizeChangeEvent)
	eVent;
	intnoowidih=see.getwidth():
	intnooHeight=see.getHeight():
	if(eontrolComPonent!=null)
	nooHeight
	eontrolComPonent.getPreferredSize().height;
	+二
	rePositionPanel(noowidth，nooHeight):
	}
	}
	validate():
	}
	voidrePositionPanel(iniwidth，intheight){
	Panel.setBounds(0，
	JMF平台开发B/S模式下多媒体计算机远程监控系统的研究与实现
	0，
	width，
	height):
	Panel·validate():
	}
	PublievoiduPdate(ReeeivestreamEventevent){
	SessionManagersouree
	=(SessiollManager)event.getsouree();
	PlayernewPlayer二null:
	if(eventinstaneeofNewReeeivestreamEvent){
	try{
	Reeeivestrearnstrealn=
	((NewReeeivestreamEvent)event).getReeeivestream();
	Datasourcedsource=stream.getDatasouree():
	newPlayer=Manager.ereatePlayer(dsource):
	}eateh(ExeePtione){
	System.err.Printin(，，RTPPlayerAPPletExeePtion”+
	e·getMessage());
	e.PrinistackTraee();
	}
	if(neWPlayer==null)笼
	return;
	}
	if(source一videomgr){
	if(videoPlayer==null){
	videoPlayer=newPlayer;
	neWPlayer.addControllerListener(this);
	neWPlaver.start();
	}
	else{
	if(Playerlist!=null)
	Playerlist.addElemeni((Objeet)neWPlayer);
	newPlayerwindow(newPlayer);
	}
	}//if(souree一videomgr)
	if(source一audiomgr){
	if(Playeriist!=null)
	Playerlist.addElemeni((Objeet)newplayer);
	newPlayerwindow(newPlayer):
	}
	}
	JMF平台开发B/S模式下多媒体计算机远程监控系统的研究与实现
	if(eventinstaneeofRemotePaxloadChangeEvent){
	}
	}
	PrivateSessiollManagerStartsessionManager(Stringdestaddrstr
	intPort，
	Stringmedia){
	SessionManagerm扣lgr=newRTPSessionMgr();
	if(media.equals(，，video”))
	videomgr=m扣mgr;
	if(media.equals(，，audio”))
	audiomgr=mynlgr;
	if(m丫叮gr==null)
	retumnull;
	mynlgr.addReeeivestreamListener(this):
	Stringename=m扣肌gr.generateCNAME():
	Stringusemame=”mf-user，，;
	SessionAddressloealaddr=newSessionAddress();
	try{
	destaddr=hietAddress.getByName(destaddrstr):
	}eateh(Un肋ow宜HostExeePtione){
	System.err.Priniln(，，inetaddress”+e.getMessage());
	e.PrintstackTrace();
	}
	SCSSionAddresSsessaddr=
	SourceDeseriPtion【]userdeselist
	inii;
	newSessiollAddress(destaddr，
	Port，
	destaddr，
	Port+1):
	=newSoureeDeseriPtion【4」;
	for(i=0;i<useuleselist.length;i++){
	if(i一0){
	useuleselist【i」=new
	SourceDescriPtion(SourceDeseriPtion.SOURCE--DESC_EMAIL，
	，，jmf-user@sun.eom”，
	l，
	false);
	eontinue;
	}
	if(i==1){
	userdeselist【i」=new
	SouxceDeseriPtion(SourceDeseriPtion.SOURCE一ESCweNAME，
	USCnlar口e
	4
	.
	弓
	JMF平台开发B/S模式下多媒体计算机远程监控系统的研究与实现
	1，
	false);
	co爪inue;
	}
	if(i==2){
	useuleselist【i」=new
	SoureeDeseriPtion(SoureeDeseriPtion.SOURCEesDESC_CNAME，
	CflamC，
	1，
	false);
	eoniinue;
	}
	if(i一3){
	userdeselist【i」=new
	SourceDeseriPtion(SourceDeseriPtion.SOURCE_DESC_TOOL，
	，，JMFRTPPlayervZ.o”，
	1，
	false);
	eontinue;
	}
	}
	try{
	m扣肌gr.initsession(loealaddr，
	m犯ngr.generateSSRC()，
	useuleselist，
	0.05，
	0.25);
	m扣叮gr.startsession(sessaddr，1，null):
	}eateh(SessionManagerExeePtione){
	System.err.Priniln(，，RTPPlayerAPPlet:RTpSM
	ExeePtion”+e.getMessage()):
	e.PrintstackTraee():
	returnnull;
	}eateh(IOExeePtione){
	System.err.Println(，，RTPPlayerAPPlet:10ExeePtion”
	+e·getMessage()):
	e.PrinistaekTrace():
	returnnull;
	}
	returnmynlgr;
	}
	}
	4注
	JMF平台开发B/S模式下多媒体计算机远程监控系统的研究与实现
	4.2.2文件媒体数据的接收和播放
	文件媒体数据的接收和播放部分实现关键步骤如下:
	1.获得媒体文件的信息。文件名由客户选择，通过asP传递给此applet程序。文件在服
	务器上的路径由函数getDoeumentBase()获得。
	if((mediaFile=getParameter(，，FILE，，))==null)
	Fatal(，，InvalidmediafileParameter，，):
	try{
	url=newURL(getDoeumentBase()，mediaFile):
	mediaFile=url.toExtemalForm();
	}eateh(MalformedURLExeePtionmue){
	}
	2.根据文件名创建JMF中的媒体定位器，返回MediaLocator类型实例mrl，用来创建
	播放器。
	try{
	if((nirl=newMediaLoeator(mediaFile))==null)
	Fatal(，，Can，tbuildURLfor”+mediaFile);
	}catch(MalformedURLExeePtione){
	Fatal(，，hivalidmediafileURL!”):
	}eateh(IOExeePtione)笼
	Fatal(，，10exeePtionereatingPlayerfor”+mrl):
	}
	3.根据rnrl创建一个播放器实例，用来接收和显示服务器传来的媒体数据。
	try{
	Player=Manager.ereatePlayer(rnrl):
	}eateh(NoPlayerExeePtione){
	System.out.Println(e):
	Fatal(，，CouldnotereatePlayerfor”+mrl);
	}
	4.为创建的播放器实例增加侦听器，以侦听播放器时间。
	P1ayer.addControllerListener(this):
	5.侦听器判断播放器的媒体时间，并根据不同事件做出不同处理。
	令如果侦听器收到播放器的实现消息，则获取播放器的视频部件和控制部件，月
	把它们增加到叩plet中。
	if(eventinstaneeofRealizeComPleteEvent){
	if(eontrolComPoneni==null)
	if((eonirolComPonent=
	Player.getControlPane1ComPoneni())!=null){
	contro1PanelHeighi=
	eontrolComPonent.getPreferredSize().height;
	Panel.add(eontrolComPonent);
	height+=controlPanelHeight;
	}
	if(visualComPonent=null)
	JMF平台开发B/S模式下多媒体计算机远程监控系统的研究与实现
	if((visualComPonent=
	Player.getVisualComPonent())卜null){
	Panel.add(visualComPoneni);
	Dimensionvideosize=visualComPonent.getPreferredsize();
	videowidih=videosize.wldth;
	videoHeight=videosize.height;
	widih=videowidih;
	height+=videoHeight:
	visualComPoneni.setBounds(0，0，videowidih，videoHeight):
	}
	Panel.setBounds(0，0，width，height);
	if(eontrolComPonent!=mill)I
	contro1ComPonent.setBounds(O，videoHeight，
	width，controlPanelHeight):
	eontrolComPoneni.invalidate();
	}
	令
	制条。
	}
	如果侦听器收到播放器的缓冲控制消息，则为播放器增加缓冲控制器和进度控
	if(eventinstaneeofCachingConiro1Eveni){
	CachingControlEvente=(CachingControlEvent)event;
	CachingConirolee=e.getCachingControl();
	if印rogressBar一null){
	if((ProgressBar=ee.getConirolComPonent())卜null)
	panel.add(progressBar);
	Panel.setsize印rogressBar.getPreferredsize());
	validate():
	}
	}
	令如果侦听器收到媒体数据结束的消息，则将播放器的媒体时间重设为零，重扫
	开始播放。
	if(eventinstaneeofEndO舰ediaEvent){
	Player.setMediaTime(newTime(0));
	player.start();
	}
	令如果侦听器收到控制出错消息，则删除Player部件，并做出错处理。
	if(eventinstaneeofControllerErrorEveni){
	Player=null;
	Fatal(((ConirollerErrorEvent)event).getMessage()):
	}
	令如果侦听器收到控制结束消息，则从叩plet的面板上删除所有的部件。
	if(eventinstaneeofConirollerClosedEvent){
	16
	JMF平台开发B/S模式下多媒体计算机远程监控系统的研究与实现
	Panel.removeAll():
	4.2.3ASP与数据库部分
	这一部分主要完成系统用户界面的显示、用户认证和权限设置等功能，土要采用asp与
	数据库实现。
	客户方浏览器中显示出监控系统的登陆界面后，只有用户正确输入用户名和口令后，才
	可以登录进入系统。其中用户的级别共有高级、中级、低级三种，分别对监控系统有不同的
	使用权限。
	用户正确登录后，浏览器显示监控系统的主界面。用户可以根据需要选择“实时监控”、
	“选择文件”、“系统设置”或“管理员”。
	如果客户端想实时监控服务器方，则选择“实时监控”，此时客户方浏览器运行包含
	Addn气pPlet.class的connectserver.asp，要求客户输入服务器方的会话地址以及视频、音频传
	输的端口和TTL参数。客户方提交后，JMF服务器收到连接，将进行RTP数据传输。客户
	方浏览器运行包含RealTimeRTpplaver.elass的RTppl即erApplet.asp，其中RealTimeRT即layer
	是运行在客户机浏览器中的javaApplet程序，负责接收服务器方发送的媒体数据。
	RTPPlayerAPplet.asp中还包含一个form，用来控制视频监控窗口的大小，用户可以在监控
	的同时，精确的调整视频监控窗口的大小。
	如果客户端选择监控服务器端的媒体文件，则在主窗口中选择“选择文件”，此时客少‘’
	方浏览器运行select--file.asP，用户可以先对文件类型进行选择，然后进一步选抒文件。)扫户
	提交后，服务器发送用户请求的文件数据，客户方运行包含Fileplayer.elass的playfile.asp，
	将服务器发送来的数据回放在浏览器中。用户可以返回seleet--file页面，重新选抒文件。
	如果用户选择“管理员”，则可以对此监控系统的管理员信息进行处理，其中管理员信
	息保存在服务器端数据库中。客户端可以进行的操作有:列表显示服务器上己有的管理员或
	增加管理员、编辑管理员的属性以及删除管理员。以下举例说明使用ASP中ADO对象实现
	web程序与数据库的连接。
	列表显示管理员信息的代码如下:
	四/o’敏示管理员信息的了程序%>
	<仑乞submylistall
	<%，定义数据源今价
	StrDSN=，，FILEDSN=gly，，
	:%，创建ADO对象咚公)
	setell二Createobjeet(，，ADODB.Conneetion，，)
	<%’扫开数据源%>
	en
	.
	OPenstrDSN
	st一、ql=，，51二11三C‘I’*任了ROMguallliyuan”
	<%执行5QL语句，选择管理员，公
	Setrs“en
	.
	I乙xeeute(strsql)
	<%，以下程序用来在table中按照权限级别列表显示管理员信息今公，
	ldl了iel(l二，，l【)，，
	<tableBORDER=‘，0，，>
	<trbgeolol=#8594b8>
	JMF平台开发B/S模式下多媒体计算机远程监控系统的研究与实现
	扔
	<fonteolor=red>
	价扮显示字段名称%>
	<%FORi=1tors下ieldsC0t一nt一1马护
	<tdalign=，，eenter，，><b><%=rs(i)入ame‘净</b></td>
	<%Next只乞>
	<tdalign=，’center”>一</td>
	</font>
	</tr>
	<%‘显示每一条记录的名字%>
	·(o/6doWhileNotrs
	.
	l二01了%>
	<%ifrs(，，niee”)>=sessio一1(，，gly-llice，，)then%>
	<trbordereolo下LavenderBlush>
	<%FORI二1TOrs卫ields
	.
	Count一1%>
	〔《场
	，LiglltseaGreeli
	thisvalue二rs(i)
	ifisnull(thisvalue)then
	thisvalue=，，”
	efldif
	ifrs(i)
	.
	Name=”Passwd，，then
	j==Len(thisvalue)
	thisvalue=stringo.”*，，)
	elzdif
	i【’rs(i)Name“，，niee‘，then
	thisvalue=ge仁nice(thisValue)
	endif
	马少
	<tdvalign二，，toP，，align=，，eenter，，bgeolo产Lavender><%=thisvalueo/of>
	</td>
	《‘毛6Next咄j>
	<%
	m又_1inkl=，，myedit
	.
	asP，，&，，?whieh=，，&rs(idField)
	nly--]inkZ=，，mydelete.asP，，&，，?whie卜，，&rs(idField)
	%>
	<tdvalign=”top，’align=，，eenter，，><ahre卜”<%二my--~linklo/de，，>编辑</价</td>
	<tdvalign二，’t叩"align二”centerl’><ahref=’，<%=my__linkZ弓冷，..>删除</护</td>
	</tT>
	<‘今6endif%>
	<%
	rs
	.
	MoveNext
	looP
	rsCI()se
	Cn
	一
	Close
	Setrs二nothing
	寸
	48
	JMF平台开发B/S模式下多媒体计算机远程监控系统的研究与实现
	seten=nothirlg
	endSub
	甘，o>
	<%I了unetionget尹ice(theniee)
	Seleetcasetheniee
	case1ge仁_nice二”高级”
	ease2ge几niee一“中级‘’
	casc3get_nice二”低级”
	EndSCleet
	1三ndl了unetion
	今吞>
	</table>
	JMF平台开发B/S模式下多媒体计算机远程监控系统的研究与实现

}
