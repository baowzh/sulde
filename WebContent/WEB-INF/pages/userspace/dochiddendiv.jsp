<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- 相册浏览 -->
<div id="selectimg"
	style="width: 690px; margin: 0px 10px 10px 10px; display: none;">
</div>
<div id="photoalbum"
	style="width: 690px; margin: 0px 10px 10px 10px; display: none;">
	<div class="flt glryBox">
		<div class="m0a" id="albumlist"
			style="width: 640px; height: 700px; margin: 0 auto">

			<div class="folder photoAlbumC">
				<div style="width: 80px; margin: 2em 1em;">
					<div class=" msheet">
						<a href="#"> <br />   <br />   <br /> <br />
							 <br />
						</a>
					</div>
				</div>
			</div>

		</div>

	</div>

</div>
<!--  -->
<!-- 图片浏览 -->
<div style="width: 690px; margin: 0px 10px 10px 10px; display: none;"
	id="imgbox">
	<div style="-webkit-writing-mode: vertical-lr;">
		<div class="flt glryBox"
			style="overflow-x: scroll; overflow-y: scroll; width: 600px;">
			<div class="m0a" style="width: 600px; height: 400px; margin: 0 auto"
				id="imglist"></div>
		</div>
		<div class="mnlist" style="text-align: center;">
			<a href="javascript:insertimg();">  </a>
		</div>
	</div>
</div>

<div id="addflash" style="display: none;z-index:99;">
	<div class="content" style="width: 150px; height: 270px;">
		<form action="addfriend.do" name="addfriendform" id="addfriendform"
			class="mglForm">
			<div class="label" style="text-align: center;">
				 </div>
			<div class="inputHolder" style="width: 32px; height: 270px;">
				<input type="text" name="flashurl" id="flashurl"
					style="-webkit-transform-origin: 10px 20px;" />
			</div>
			<div class="mnlist" style="height: 270px;"></div>
			<div class="mnlist" style="height: 270px; text-align: center;">
				<a href="javascript:insertvideo();"> </a>
			</div>
		</form>
	</div>
</div>
<div id="addlink" style="display: none">
	<div class="content"
		style="padding-left: 30px; width: 170px; height: 270px;">
		<form action="addfriend.do" name="addfriendform" id="addfriendform"
			class="mglForm">
			<div class="label" style="text-align: center;">
				 </div>
			<div class="inputHolder" style="width: 32px; height: 270px;">
				<input type="text" name="linkurldes" id="linkurldes"
					style="-webkit-transform-origin: 10px 20px;" />
			</div>
			<div class="label" style="text-align: center;"> 
				</div>
			<div class="inputHolder" style="width: 32px; height: 270px;">
				<input type="text" name="linkurl" id="linkurl"
					style="-webkit-transform-origin: 10px 20px;" />
			</div>
			<div class="mnlist" style="height: 270px;"></div>
			<div class="mnlist" style="height: 270px; text-align: center;">
				<a href="javascript:inseralink();"> </a>
			</div>
		</form>
	</div>
</div>
<div id="addimg" style="display: none">
	<div class="content" style="width: 150px; height: 270px;">
		<form action="addfriend.do" name="addfriendform" id="addfriendform"
			class="mglForm">
			<div class="label" style="text-align: center;"> 
				</div>
			<div class="inputHolder" style="width: 32px; height: 270px;">
				<input type="text" name="imgurl" id="imgurl"
					style="-webkit-transform-origin: 10px 20px;" />
			</div>
			<div class="mnlist" style="height: 270px;"></div>
			<div class="mnlist" style="height: 270px; text-align: center;">
				<a href="javascript:addimg();"> </a>
			</div>
		</form>
	</div>
</div>
<div id="addmp3" style="display: none">
	<div class="content" style="width: 150px; height: 270px;">
		<form action="addfriend.do" name="addfriendform" id="addfriendform"
			class="mglForm">
			<div class="label" style="text-align: center;">MP3  </div>
			<div class="inputHolder" style="width: 32px; height: 270px;">
				<input type="text" name="mp3url" id="mp3url"
					style="-webkit-transform-origin: 10px 20px;" />
			</div>
			<div class="mnlist" style="height: 270px;"></div>
			<div class="mnlist" style="height: 270px; text-align: center;">
				<a href="javascript:insertmp3();"> </a>
			</div>
		</form>
	</div>
</div>