<%@ include file="init.jsp" %>
<%@page import="com.hbg.rp.search.constants.ApplicationConstant"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.util.PropsUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletContext"%>

<%
String coverImgURL=PropsUtil.get("cover.image.url");
LiferayPortletContext context = (LiferayPortletContext) portletConfig.getPortletContext();
long timestamp = context.getPortlet().getTimestamp();
%>

<c:set var="loadMoreType" value="${formparam['catalog-search-param']}" />

<span style="display:none;" id="main_wrapper_content">
	<c:choose>
		<c:when
			test="${!empty data && !empty data.catalogData && data.catalogData.size() > 0}">
			<p>
				1-<span id="current-size-catalogs">${data.catalogData.size()}</span>
				of <span id="total-size-catalogs"> <b><fmt:formatNumber
							type="number" groupingUsed="true"
							value="${formparam['resultSize']}" /></b>
				</span>
				<c:choose>
					<c:when
						test="${!empty formparam['searchParam'] && data.catalogData.size() == 1}"> Title/Item found ${fn:replace(fn:replace(formparam['searchParam'],'>','&gt;'),'<','&lt;')} </c:when>
					<c:otherwise>
							Titles/Items found <c:if
							test="${!empty formparam['searchParam'] }">  ${fn:replace(fn:replace(formparam['searchParam'],'>','&gt;'),'<','&lt;')} </c:if>
					</c:otherwise>
				</c:choose>
			</p>
		</c:when>
		<c:otherwise>
			<p>
				No Titles/Items found
				<c:if test="${!empty formparam['searchParam']}"> ${fn:replace(fn:replace(formparam['searchParam'],'>','&gt;'),'<','&lt;')}</c:if>
			</p>
		</c:otherwise>
	</c:choose>
</span>
<span style="display:none;" id="excelExport">
	<p>Viewing Titles/Items</p>
</span>
<script>
	$('.main-wrapper .row .text-left').html($('#main_wrapper_content').html());	
	$('.main-wrapper .row .text-right').html($('#excelExport').html());
</script>

<div class="recent-orders" id="result-catalogs">

	<div class="catalogs_rows">

		<c:choose>
			<c:when test="${!empty data && !empty data.catalogData}">
				<c:forEach items="${data.catalogData}" var="catalogdata"
					varStatus="count">
					<div class="recent-orders">
						<div class="table_outer">
							<header class="table_header">
								<span class="serial_number">${count.index + 1 + formparam['catalogs-offset'] }.</span>
							</header>
							<div class="body_content">
								<div class="table_content">
									<div class="catalog-search-table">
										<div class="media seller_detail">
											<div class="media-left">
												<a
													href="<%=ApplicationConstant.VIEW_CATALOG_BOOK_URL %>${catalogdata.isbn}">
													<img
													onerror="this.src='<%=request.getContextPath()%>/images/default-book.svg'"
													class="media-object"
													src="<%=coverImgURL%>${catalogdata.isbn}/"
													alt="No Image Found" width="130">
												</a>
											</div>
											<portlet:renderURL var="catalogDetailURL"
												windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>">
												<portlet:param name="action" value="getCatalogDetailPage" />
												<portlet:param name="isbn" value="${catalogdata.isbn}" />
											</portlet:renderURL>
											<div class="media-body">
												<h4>${catalogdata.title}</h4>
												<p>
                                                    <c:if test="${not empty catalogdata.byLine}">
                                                        <b>By:</b> ${catalogdata.byLine}
                                                    </c:if>
												</p>
												<p>
													<b>Format:</b> ${catalogdata.productDesc}
												</p>
												<p>
													<b>ISBN-13:</b> ${catalogdata.isbn}
												</p>
												<p>
													<b>On Sale:</b>
													<fmt:parseDate var="onSaleDt"
														value="${catalogdata.onSaleDate}" pattern="yyyy-MM-dd" />
													<fmt:formatDate value="${onSaleDt}" dateStyle="long" />
												</p>
												<p>
													<b>Publisher:</b> ${catalogdata.publisherDesc}
												</p>
												<p>
													<b>Imprint:</b> ${catalogdata.imprint}
												</p>
												<p>
													<b>Price:</b>

													<c:if
														test="${not empty catalogdata.usPrice and catalogdata.usPrice ne '0'}">
														<c:choose>
															<c:when
																test="${catalogdata.usPrice eq '0.00' or catalogdata.usPrice eq '0.0'}">
																  			 $${catalogdata.usPrice}
																    </c:when>
															<c:when test="${catalogdata.usPrice eq 'N/A'}">
																  			 ${catalogdata.usPrice}
																    </c:when>
															<c:otherwise>
																    	 $<fmt:formatNumber type="number"
																	minFractionDigits="2" maxFractionDigits="2"
																	value="${catalogdata.usPrice}" />
																<c:if
																	test="${not empty catalogdata.isTentative and catalogdata.isTentative eq 'Y'}">
																		   (tentative)
																		 </c:if>
															</c:otherwise>
														</c:choose>
													</c:if>

													<c:if
														test="${not empty catalogdata.canPrice  and catalogdata.canPrice ne '0'}">

														<c:choose>
															<c:when
																test="${catalogdata.canPrice eq '0.00' or catalogdata.canPrice eq '0.0'}">
																  			 ($${catalogdata.canPrice}  in Canada)
																    </c:when>
															<c:when test="${catalogdata.canPrice eq 'N/A'}">
																  			 (${catalogdata.canPrice}  in Canada)
																    </c:when>
															<c:otherwise>
																    		 ($<fmt:formatNumber type="number"
																	minFractionDigits="2" maxFractionDigits="2"
																	value="${catalogdata.canPrice}" />
																<c:if
																	test="${not empty catalogdata.isTentative and catalogdata.isTentative eq 'Y'}">
																			   (tentative)
																			 </c:if>   in Canada)
																    </c:otherwise>
														</c:choose>

													</c:if>
											</p>
											<p>
												<c:if test="${not empty catalogdata.teamSize}">
                                            		<b>Size:</b><span class="mr-3"> ${fn:replace(catalogdata.teamSize, '&quot;', '')} inches </span>
                                            	</c:if>
                                            	<c:if test="${not fn:contains(catalogdata.productDesc, 'Promo Posters') and not fn:contains(catalogdata.productDesc, 'Foam Back Print (Poster)') and not fn:contains(catalogdata.productDesc, 'PT internal use only') and not fn:contains(catalogdata.productDesc, 'Music') and not fn:contains(catalogdata.productDesc, 'Promotional Item - Non Distributed') and not fn:contains(catalogdata.productDesc, 'Promotional Item - Distributed') and not fn:contains(catalogdata.productDesc, 'Audio Book') and not fn:contains(catalogdata.productDesc, 'Calendars') and not fn:contains(catalogdata.productDesc, 'Games') and not fn:contains(catalogdata.productDesc, 'Cards') and not fn:contains(catalogdata.productDesc, 'Stationery') and not fn:contains(catalogdata.productDesc, 'Flash Cards') and not fn:contains(catalogdata.productDesc, 'Postcard (Book or Pack)') and not fn:contains(catalogdata.productDesc, 'Magazine') and not fn:contains(catalogdata.productDesc, 'Stickers') and not empty catalogdata.nbrOfPages and catalogdata.nbrOfPages ne 0}">
                                            		<b>Pages:</b> ${catalogdata.nbrOfPages}
                                                </c:if>
											</p>
											<p>
												<c:if
													test="${not fn:contains(catalogdata.productDesc, 'Promo Posters') and not fn:contains(catalogdata.productDesc, 'Foam Back Print (Poster)') and not fn:contains(catalogdata.productDesc, 'PT internal use only') and not fn:contains(catalogdata.productDesc, 'Promotional Item -� Non Distributed') and not fn:contains(catalogdata.productDesc, 'Promotional Item - Distributed') and not fn:contains(catalogdata.productDesc, 'Magazine') and not fn:contains(catalogdata.productDesc, 'Stickers') and not empty catalogdata.unitWeight}">
													<b>Shipping Weight:</b> ${catalogdata.unitWeight} lbs
   												</c:if>
											</p>
											</div>
										</div>
										<div class="right_content">

											<a class="btn btn-theme shipment-data" style="height:30px;width:294px;"
												href="<%=ApplicationConstant.VIEW_CATALOG_BOOK_URL %>${catalogdata.isbn}">
												View Complete Information</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:when>
		</c:choose>

		<div class="append_div">
			<c:if
				test="${not empty formparam['catalogs-loadmore'] && formparam['catalogs-loadmore'] eq 'true'}">
				<div class="row text-center">
					<div class="col-xs-12">
						<button id="loadMore-catalogs" class="btn btn-theme loadMore"
							name="${loadMoreType}">
							Load Next
							<%=ApplicationConstant.PAGE_SIZE%>
							Results
						</button>
					</div>
				</div>
			</c:if>
			<div id="catalogs-loading" class=""></div>
		</div>
	</div>
</div>

<c:if test="${not empty loadMoreType}">
	<script type="text/javascript">
		document.title = 'HBG Retailer Portal: Title/Item Search Results';
	</script>
</c:if>
<script src="<%=request.getContextPath()%>/js/clear_form.js?t=<%=timestamp%>"
	type="text/javascript"></script>