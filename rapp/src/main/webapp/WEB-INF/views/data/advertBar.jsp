<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div class="col-md-12">
	<div class="tabs-container">
		<ul class="nav nav-tabs">
			<li class=""><a data-toggle="tab" href="#advertBar-year"
				aria-expanded="false">年</a></li>
			<li class=""><a data-toggle="tab" href="#advertBar-month"
				aria-expanded="false">月</a></li>
			<li class="active"><a data-toggle="tab" href="#advertBar-day"
				aria-expanded="true">日</a></li>
			<li class=""><a data-toggle="tab" href="#advertBar-hour"
				aria-expanded="false">时</a></li>
		</ul>
		<input type="hidden" id="advertBar-type" value="D">
	</div>
	<div>
    <div class="tab-content">
        <div id="advertBar-year" class="tab-pane">
            <br/>
            <!-- 按年统计 -->
            <div class="col-md-12">
				<div class="alert alert-info alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>温馨提示：</strong> 本统计数据默认统计今年您名下广告数据，您也可以自己选择时间范围和指定广告来统计数据！
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-5">
					<form id="advertBarYearForm">
						<div class="form-group date-year">
				            <div class="input-daterange input-group">
				            	<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
				                <input type="text" class="form-control" id="advertBarYear-startTime" placeholder="推送起始年份" />
				                <span class="input-group-addon">至</span>
				                <input type="text" class="form-control" id="advertBarYear-endTime" placeholder="推送结束年份" />
				            </div>
				        </div>
			        </form>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<select id="advertBarYear-rno" class="selectpicker show-menu-arrow"
							title="选择要统计的广告" data-size="6">
							<option value="">全部</option>
							<c:choose>
								<c:when test="${ads.success }">
									<c:forEach var="a" items="${ads.data}">
										<option value="${a.advertId }">${a.advertName }</option>
									</c:forEach>
								</c:when>
							</c:choose>
						</select> <span class="help-block m-b-none"></span>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<button class="btn btn-primary" type="button"
							onclick="ds.advertBar();">
							<span class="glyphicon glyphicon-search"></span> 查询
						</button>
					</div>
				</div>
			</div>
        </div>

        <div id="advertBar-month" class="tab-pane">
            <br/>
            <!-- 按月统计 -->
            <div class="col-md-12">
				<div class="alert alert-info alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>温馨提示：</strong> 本统计数据默认统计今年每月您名下广告数据，您也可以自己选择时间范围和指定广告来统计数据！
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-5">
					<form id="advertBarMonthForm">
						<div class="form-group date-month">
				            <div class="input-daterange input-group">
				            	<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
				                <input type="text" class="form-control" id="advertBarMonth-startTime" placeholder="推送起始时间" />
				                <span class="input-group-addon">至</span>
				                <input type="text" class="form-control" id="advertBarMonth-endTime" placeholder="推送结束时间" />
				            </div>
				        </div>
			        </form>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<select id="advertBarMonth-rno" class="selectpicker show-menu-arrow"
							title="选择要统计的广告" data-size="6">
							<option value="">全部</option>
							<c:choose>
								<c:when test="${ads.success }">
									<c:forEach var="a" items="${ads.data}">
										<option value="${a.advertId }">${a.advertName }</option>
									</c:forEach>
								</c:when>
							</c:choose>
						</select> <span class="help-block m-b-none"></span>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<button class="btn btn-primary" type="button"
							onclick="ds.advertBar();">
							<span class="glyphicon glyphicon-search"></span> 查询
						</button>
					</div>
				</div>
			</div>
        </div>
        <div id="advertBar-day" class="tab-pane active">
        	<br/>
            <!-- 按日统计 -->
            <div class="col-md-12">
				<div class="alert alert-info alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>温馨提示：</strong> 本统计数据默认统计近半月您名下广告数据，您也可以自己选择时间范围和指定广告来统计数据！
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-5">
					<form id="advertBarForm">
						<div class="form-group date-day">
				            <div class="input-daterange input-group">
				            	<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
				                <input type="text" class="form-control" id="advertBar-startTime" placeholder="推送起始时间"  />
				                <span class="input-group-addon">至</span>
				                <input type="text" class="form-control" id="advertBar-endTime" placeholder="推送结束时间"  />
				            </div>
				        </div>
			        </form>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<select id="advertBarDay-rno" class="selectpicker show-menu-arrow"
							title="选择要统计的广告" data-size="6">
							<option value="">全部</option>
							<c:choose>
								<c:when test="${ads.success }">
									<c:forEach var="a" items="${ads.data}">
										<option value="${a.advertId }">${a.advertName }</option>
									</c:forEach>
								</c:when>
							</c:choose>
						</select> <span class="help-block m-b-none"></span>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<button class="btn btn-primary" type="button"
							onclick="ds.advertBar();">
							<span class="glyphicon glyphicon-search"></span> 查询
						</button>
					</div>
				</div>
			</div>
        </div>
        <!-- 按小时统计 -->
        <div id="advertBar-hour" class="tab-pane">
            <br/>
            <!-- 按小时统计 -->
            <div class="col-md-12">
				<div class="alert alert-info alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>温馨提示：</strong> 本统计数据默认统计昨日您名下广告数据，您也可以自己选择时间范围和指定广告来统计数据！
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-4">
					<form id="advertBarHourForm">
						<div class="form-group date-day">
				            <div class="input-group date" data-autoclose="true" >
                                <span class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</span>
                                <input type="text" class="form-control" id="advertBar-pushTime" placeholder="推送日期" />
                            </div>
				        </div>
			        </form>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<select id="advertBarHour-rno" class="selectpicker show-menu-arrow"
							title="选择要统计的广告" data-size="6">
							<option value="">全部</option>
							<c:choose>
								<c:when test="${ads.success }">
									<c:forEach var="a" items="${ads.data}">
										<option value="${a.advertId }">${a.advertName }</option>
									</c:forEach>
								</c:when>
							</c:choose>
						</select> <span class="help-block m-b-none"></span>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<button class="btn btn-primary" type="button"
							onclick="ds.advertBar();">
							<span class="glyphicon glyphicon-search"></span> 查询
						</button>
					</div>
				</div>
			</div>
        </div>
    </div>

</div>
</div>

<br/>


<div class="col-md-12">
	<div id="advertBar" style="width: 100%; height: 400px;"></div>
</div>

<div class="col-md-12" id="advertBar-detail-div" style="display: none;">
	<table id="table_advertlogs" class="grid-table"></table>
</div>