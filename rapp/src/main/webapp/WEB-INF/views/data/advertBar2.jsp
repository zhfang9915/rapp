<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div class="col-md-12">
	<div class="col-md-7">
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
	            <strong>排版与链接</strong>
	
	            <p>Bootstrap 排版、链接样式设置了基本的全局样式。分别是： 为 body 元素设置 background-color: #fff; 使用 @font-family-base、@font-size-base 和 @line-height-base a变量作为排版的基本参数 为所有链接设置了基本颜色 @link-color ，并且当链接处于 :hover 状态时才添加下划线 这些样式都能在 scaffolding.less 文件中找到对应的源码。</p>
	        </div>
	
	        <div id="advertBar-month" class="tab-pane">
	            <strong>Normalize.css</strong>
	
	            <p>为了增强跨浏览器表现的一致性，我们使用了 Normalize.css，这是由 Nicolas Gallagher 和 Jonathan Neal 维护的一个CSS 重置样式库。</p>
	        </div>
	        <div id="advertBar-day" class="tab-pane active">
	        	<br/>
	            <!-- 按月统计 -->
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group">
							<input type="text" id="advertBar-startTime"
								class="form-control layer-date" placeholder="推送起始时间">
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<input type="text" id="advertBar-endTime"
								class="form-control layer-date" placeholder="推送起始时间">
						</div>
					</div>
				</div>
	        </div>
	        <!-- 按日统计 -->
	        <div id="advertBar-hour" class="tab-pane">
	            <br/>
	            <!-- 按月统计 -->
				<div class="col-md-12">
					<div class="form-group">
						<input type="text" id="advertBar-pushTime"
							class="form-control layer-date" placeholder="推送时间">
					</div>
				</div>
	        </div>
	    </div>
	
	</div>
	</div>
	<div class="col-md-5">
		<div class="row" style="margin-top: 55px;">
			<div class="col-md-8">
				<div class="form-group text-center">
					<select name="advertBar-rno" class="selectpicker show-menu-arrow"
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
			<div class="col-md-4">
				<div class="form-group text-center">
					<button class="btn btn-primary pull-right" type="button"
						onclick="ds.advertBar();">
						<span class="glyphicon glyphicon-search"></span> 查询
					</button>
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
	<input type="hidden" id="search-startTime"> <input
		type="hidden" id="search-endTime">
	<table id="table_advertlogs" class="grid-table"></table>
</div>