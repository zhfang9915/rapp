<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div id="upload_modal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">本地上传图片</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="uploadForm">
					<div class="form-group has-feedback">
						<label class="col-sm-3 control-label">上传文件</label>
						<div class="col-sm-9">
							<input type="hidden" id="res-id">
							<input type="file" class="form-control" name="advert_file"/>
							<span class="help-block m-b-none">为了保证文件的有效性，请勿使用中文命名文件！</span> 
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<!--验证信息-->
				<span id="err_msg" class="glyphicon"></span>
				<button type="button" id="btn-upload" class="btn btn-primary" onclick="advert.uploadAdvertFile();">上&nbsp;&nbsp;传</button>
			</div>
		</div> <!-- /.modal-content -->
	</div> <!-- /.modal-dialog -->
</div> <!-- /.modal -->