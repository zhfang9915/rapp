<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div class="col-md-12">
	<div class="tabs-container">
		<ul class="nav nav-tabs">
			<li class=""><a data-toggle="tab" href="#routerBar-year"
				aria-expanded="false">年</a></li>
			<li class=""><a data-toggle="tab" href="#routerBar-month"
				aria-expanded="false">月</a></li>
			<li class="active"><a data-toggle="tab" href="#routerBar-day"
				aria-expanded="true">日</a></li>
			<li class=""><a data-toggle="tab" href="#routerBar-hour"
				aria-expanded="false">时</a></li>
		</ul>
	</div>
	<div>
	    <div class="tab-content">
	        <div id="routerBar-year" class="tab-pane">
				<br/>
				<!-- 按年统计 -->
				<div class="col-md-12">
					<div class="alert alert-info alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>温馨提示：</strong> 本统计数据默认统计您名下所有设备今年推送的广告数据，您也可以自己选择时间范围和指定设备来统计数据！
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-5">
						<div class="form-group date-year">
				            <div class="input-daterange input-group">
				            	<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
				                <input type="text" class="form-control" id="routerBarYear-startTime" placeholder="推送起始年份" />
				                <span class="input-group-addon">至</span>
				                <input type="text" class="form-control" id="routerBarYear-endTime" placeholder="推送结束年份" />
				            </div>
				        </div>
					</div>
					<div class="col-md-4">
						<div class="form-group text-center">
							<select id="routerBarYear-rno" class="selectpicker show-menu-arrow"
								title="选择要统计的设备" data-size="6">
								<option value="">全部</option>
								<c:choose>
									<c:when test="${routers.success }">
										<c:forEach var="r" items="${routers.data}">
											<option value="${r.devNo }">${r.devName }</option>
										</c:forEach>
									</c:when>
								</c:choose>
							</select> <span class="help-block m-b-none"></span>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group text-center">
							<button class="btn btn-primary" type="button"
								onclick="ds.routerBar();">
								<span class="glyphicon glyphicon-search"></span> 查询
							</button>
						</div>
					</div>
				</div>
			</div>
	        <div id="routerBar-month" class="tab-pane">
				<br/>
				<!-- 按月统计 -->
				<div class="col-md-12">
					<div class="alert alert-info alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>温馨提示：</strong> 本统计数据默认统计您名下所有设备今年每月推送的广告数据，您也可以自己选择时间范围和指定设备来统计数据！
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-5">
						<div class="form-group date-month">
				            <div class="input-daterange input-group">
				            	<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
				                <input type="text" class="form-control" id="routerBarMonth-startTime" placeholder="推送起始时间" />
				                <span class="input-group-addon">至</span>
				                <input type="text" class="form-control" id="routerBarMonth-endTime" placeholder="推送结束时间" />
				            </div>
				        </div>
					</div>
					<div class="col-md-4">
						<div class="form-group text-center">
							<select id="routerBarMonth-rno" class="selectpicker show-menu-arrow"
								title="选择要统计的设备" data-size="6">
								<option value="">全部</option>
								<c:choose>
									<c:when test="${routers.success }">
										<c:forEach var="r" items="${routers.data}">
											<option value="${r.devNo }">${r.devName }</option>
										</c:forEach>
									</c:when>
								</c:choose>
							</select> <span class="help-block m-b-none"></span>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group text-center">
							<button class="btn btn-primary" type="button"
								onclick="ds.routerBar();">
								<span class="glyphicon glyphicon-search"></span> 查询
							</button>
						</div>
					</div>
				</div>
			</div>
	        <div id="routerBar-day" class="tab-pane active">
				<br/>
				<!-- 按日统计 -->
				<div class="col-md-12">
					<div class="alert alert-info alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>温馨提示：</strong> 本统计数据默认统计您名下所有设备近半月推送的广告数据，您也可以自己选择时间范围和指定设备来统计数据！
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-5">
						<div class="form-group date-day">
				            <div class="input-daterange input-group">
				            	<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
				                <input type="text" class="form-control" id="routerBarDay-startTime" placeholder="推送起始时间"  />
				                <span class="input-group-addon">至</span>
				                <input type="text" class="form-control" id="routerBarDay-endTime" placeholder="推送结束时间"  />
				            </div>
				        </div>
					</div>
					<div class="col-md-4">
						<div class="form-group text-center">
							<select id="routerBarDay-rno" class="selectpicker show-menu-arrow"
								title="选择要统计的设备" data-size="6">
								<option value="">全部</option>
								<c:choose>
									<c:when test="${routers.success }">
										<c:forEach var="r" items="${routers.data}">
											<option value="${r.devNo }">${r.devName }</option>
										</c:forEach>
									</c:when>
								</c:choose>
							</select> <span class="help-block m-b-none"></span>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group text-center">
							<button class="btn btn-primary pull-right" type="button"
								onclick="ds.routerBar();">
								<span class="glyphicon glyphicon-search"></span> 查询
							</button>
						</div>
					</div>
				</div>
			</div>
	        <div id="routerBar-hour" class="tab-pane">
				<br/>
				<!-- 按时统计 -->
				<div class="col-md-12">
					<div class="alert alert-info alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>温馨提示：</strong> 本页数据默认统计您名下所有设备昨日推送的广告数据，您也可以自己选择指定日期或指定设备统计数据！
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-5">
						<div class="form-group date-day">
				            <div class="input-group date" data-autoclose="true" >
                                <span class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</span>
                                <input type="text" class="form-control" id="routerBarHour-pushTime" placeholder="推送日期" />
                            </div>
				        </div>
					</div>
					<div class="col-md-4">
						<div class="form-group text-center">
							<select id="routerBarHour-rno" class="selectpicker show-menu-arrow"
								title="选择要统计的设备" data-size="6">
								<option value="">全部</option>
								<c:choose>
									<c:when test="${routers.success }">
										<c:forEach var="r" items="${routers.data}">
											<option value="${r.devNo }">${r.devName }</option>
										</c:forEach>
									</c:when>
								</c:choose>
							</select> <span class="help-block m-b-none"></span>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group text-center">
							<button class="btn btn-primary" type="button"
								onclick="ds.routerBar();">
								<span class="glyphicon glyphicon-search"></span> 查询
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="col-md-12">
	<div id="routerBar" style="width: 100%; height: 400px;"></div>
</div>
<div class="col-md-12" id="routerBar-detail-div" style="display: none;">
<!-- 	<input type="hidden" id="search-startTime"> -->
<!-- 	<input type="hidden" id="search-endTime"> -->
<!-- 	<input type="hidden" id="search-pushTime"> -->
<!-- 	<input type="hidden" id="search-devNo"> -->
	<table id="table_advertlogsByRouter" class="grid-table"></table>
</div>