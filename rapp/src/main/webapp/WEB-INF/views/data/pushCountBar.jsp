<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div class="col-md-12">
	<div class="alert alert-info alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<strong>注意：</strong> 本统计数据默认统计您名下广告所有的数据，您也可以自己选择时间范围来统计指定日期内的数据！
	</div>
</div>
<div class="col-md-12">
	<div class="col-md-8">
		<form id="pushCountBarForm">
			<div class="form-group date-day">
	            <div class="input-daterange input-group">
	            	<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                <input type="text" class="form-control" id="pushCountBar-startTime" placeholder="推送起始时间"  />
	                <span class="input-group-addon">至</span>
	                <input type="text" class="form-control" id="pushCountBar-endTime" placeholder="推送结束时间"  />
	            </div>
	        </div>
        </form>
	</div>
	<div class="col-md-4">
		<div class="form-group text-center">
			<button class="btn btn-primary" type="button"
				onclick="ds.showPushCountBar();">
				<span class="glyphicon glyphicon-search"></span> 查询
			</button>
		</div>
	</div>
</div>
<div class="col-md-12">
	<div id="pushCountBar" style="width: 100%; height: 450px;"></div>
</div>

<div class="col-md-12" id="pushCountBar-detail-div" style="display: none;">
	<table id="table_advertNamelogs" class="grid-table"></table>
</div>