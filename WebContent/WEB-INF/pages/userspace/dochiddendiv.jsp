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
						<a href="#"> <br />  <br />  <br /> <br />
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

<div id="addflash" style="display: none">
	<div class="flt" style="padding-left: 5px;">
		<form action="addfriend.do" name="addfriendform" id="addfriendform">
			<div class="inputHolder" style="width: 100px; height: 270px">
				<input type="text" name="flashurl" id="flashurl"
					style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr;" />
			</div>

			<div class="mnlist">
				<a href="javascript:insertvideo();"> </a>
			</div>
		</form>
	</div>
</div>
<div id="addlink" style="display: none">
	<div class="flt">
		<form action="addfriend.do" name="addfriendform" id="addfriendform">
			<div class=inputHolder style="width: 100px; height: 270px">
				<input type="text" name="linkurl" id="linkurl"
					style="writing-mode: tb-lr; -webkit-writing-mode: vertical-lr;" />
			</div>

			<div class="mnlist">
				<a href="javascript:inseralink();"> </a>
			</div>
		</form>
	</div>
</div>
<!-- <div class="m1ln" style="width: 85px; height: 400px; display: none;" -->
<!-- 	id="addemface"> -->
<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<div class="i" style="width: 42px; height: 42px"> -->
<!-- 					<a href="javascript:addemotion('face1');"><img -->
<!-- 						src="img/faces/face1.png" width="40" height="40" /></a> -->
<!-- 				</div> -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 				<div class="i" style="width: 42px; height: 42px"> -->
<!-- 					<a href="javascript:addemotion('face2');"><img -->
<!-- 						src="img/faces/face2.png" width="40" height="40" /></a> -->
<!-- 				</div> -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 				<div class="i" style="width: 42px; height: 42px"> -->
<!-- 					<a href="javascript:addemotion('face3');"><img -->
<!-- 						src="img/faces/face3.png" width="40" height="40" /></a> -->
<!-- 				</div> -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 				<div class="i" style="width: 42px; height: 42px"> -->
<!-- 					<a href="javascript:addemotion('face4');"><img -->
<!-- 						src="img/faces/face4.png" width="40" height="40" /></a> -->
<!-- 				</div> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<div class="i" style="width: 42px; height: 42px"> -->
<!-- 					<a href="javascript:addemotion('face5');"><img -->
<!-- 						src="img/faces/face5.png" width="40" height="40" /></a> -->
<!-- 				</div> -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 				<div class="i" style="width: 42px; height: 42px"> -->
<!-- 					<a href="javascript:addemotion('face6');"><img -->
<!-- 						src="img/faces/face6.png" width="40" height="40" /></a> -->
<!-- 				</div> -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 				<div class="i" style="width: 42px; height: 42px"> -->
<!-- 					<a href="javascript:addemotion('face7');"><img -->
<!-- 						src="img/faces/face7.png" width="40" height="40" /></a> -->
<!-- 				</div> -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 				<div class="i" style="width: 42px; height: 42px"> -->
<!-- 					<a href="javascript:addemotion('face8');"><img -->
<!-- 						src="img/faces/face8.png" width="40" height="40" /></a> -->
<!-- 				</div> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<div class="i" style="width: 42px; height: 42px"> -->
<!-- 					<a href="javascript:addemotion('face9');"><img -->
<!-- 						src="img/faces/face9.png" width="40" height="40" /></a> -->
<!-- 				</div> -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 				<div class="i" style="width: 42px; height: 42px"> -->
<!-- 					<a href="javascript:addemotion('face10');"><img -->
<!-- 						src="img/faces/face10.png" width="40" height="40" /></a> -->
<!-- 				</div> -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 				<div class="i" style="width: 42px; height: 42px"> -->
<!-- 					<a href="javascript:addemotion('face11');"><img -->
<!-- 						src="img/faces/face11.png" width="40" height="40" /></a> -->
<!-- 				</div> -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 				<div class="i" style="width: 42px; height: 42px"> -->
<!-- 					<a href="javascript:addemotion('face12');"><img -->
<!-- 						src="img/faces/face12.png" width="40" height="40" /></a> -->
<!-- 				</div> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<div class="i" style="width: 42px; height: 42px"> -->
<!-- 					<a href="javascript:addemotion('face13');"><img -->
<!-- 						src="img/faces/face13.png" width="40" height="40" /></a> -->
<!-- 				</div> -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 				<div class="i" style="width: 42px; height: 42px"> -->
<!-- 					<a href="javascript:addemotion('face14');"><img -->
<!-- 						src="img/faces/face14.png" width="40" height="40" /></a> -->
<!-- 				</div> -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 				<div class="i" style="width: 42px; height: 42px"> -->
<!-- 					<a href="javascript:addemotion('face15');"><img -->
<!-- 						src="img/faces/face15.png" width="40" height="40" /></a> -->
<!-- 				</div> -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 				<div class="i" style="width: 42px; height: 42px"> -->
<!-- 					<a href="javascript:addemotion('face16');"><img -->
<!-- 						src="img/faces/face16.png" width="40" height="40" /></a> -->
<!-- 				</div> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->
<!-- </div> -->
<!--  -->