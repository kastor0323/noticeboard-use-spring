<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
    
<%@include file="../includes/header.jsp"%>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board List Page
                            <button id='regBtn' type="button" class="btn btn-xs pull-right">Register
                            New Board</button> 
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>BNO</th>
                                        <th>Title</th>
                                        <th>Writer</th>
                                        <th>RegDate</th>
                                        <th>UpdateDate</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list}" var="board">
                                    <tr class="odd gradeX">
                                        <td><c:out value="${board.bno}"/></td>
                                        <td><a href="/board/get?bno=<c:out value="${board.bno}"/>"><c:out value="${board.title}"/></td>
                                        <td><c:out value="${board.writer}"/></td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}"/></td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate}"/></td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->

                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            <div id ="myModal"class="modal" tabindex="-1" role="dialog">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">Modal title</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        <p>Modal body text goes here.</p>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-primary">Save changes</button>
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			      </div>
			    </div>
			  </div>
			</div>
			<script>
			$(document).ready(function(){
			    var result = '<c:out value="${result}"/>';
			
			    checkModal(result);
			    
			    history.replaceState({},null, null); //게시물 등록 후 뒤로가기 한 후 다시 앞으로 갈 때 
			    //모달창이 나오지 않게 링크를 초기화 histry.state일 때 이런 센스가 필요하다
			
			    function checkModal(result){
			        if(result === '' || history.state ){
			            return;
			        }
			        
			        if(result === "success"){
			            $(".modal-body").html(
			                "정상적으로 처리되었습니다.");
			        }else if(parseInt(result)>0){
			        	$(".modal-body").html(
				           "게시글 " + parseInt(result) + " 번이 등록되었습니다.");
			        }
			        
			        $("#myModal").modal("show");
			   		}
			
			    $("#regBtn").click(function() {
			        self.location = "/board/register";
			    });
			});
			</script>
			 
  <%@include file="../includes/footer.jsp"%>