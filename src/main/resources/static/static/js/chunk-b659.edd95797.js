(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-b659"],{"8QsK":function(t,e,n){"use strict";n.r(e);var i=n("AxDu"),a={name:"Applyinfofile",data:function(){return{zsDialog:!1,firstDialog:!1,list:void 0,listQuery:{applyId:null,list:null},value:!0}},created:function(){this.getParams(),this.getFileData()},methods:{getParams:function(){var t=this.$route.query.applyId;this.listQuery.applyId=t},getFileData:function(){var t=this;Object(i.f)(this.listQuery).then(function(e){t.list=e.data.files,t.listQuery.applyId=e.data.applyId})},firstshenhe:function(){this.firstDialog=!0,this.listQuery.list=this.list},endshenhe:function(){this.zsDialog=!0,this.listQuery.list=this.list},confirmmethod:function(t){var e=this;this.zsDialog=!1,t&&Object(i.d)(this.listQuery).then(function(t){var n=t.status,i=t.msg;200===n&&e.$notify({title:"成功",message:i,type:"success",duration:2e3})})},gtconfirmmethod:function(t){var e=this;this.firstDialog=!1,t&&Object(i.g)(this.listQuery).then(function(t){var n=t.status,i=t.msg;200===n&&e.$notify({title:"成功",message:i,type:"success",duration:2e3})})}}},s=(n("Rs8F"),n("KHd+")),o=Object(s.a)(a,function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"app-container"},[n("div",t._l(t.list,function(e){return n("el-card",{key:e.id,staticClass:"box-card"},[n("div",{attrs:{slot:"header"},slot:"header"},[t._v("\n        "+t._s(e.dictName)+"\n      ")]),t._v(" "),n("div",{staticClass:"box-item"},[n("img",{staticStyle:{width:"100%","min-height":"500px"},attrs:{src:e.fileUrl}}),t._v(" "),n("el-input",{attrs:{rows:2,type:"textarea",placeholder:"审批不通过的理由/原因"},model:{value:e.note,callback:function(n){t.$set(e,"note",n)},expression:"item.note"}}),t._v(" "),n("el-switch",{attrs:{"active-color":"#13ce66","inactive-color":"#ff4949","active-text":"通过","inactive-text":"未通过"},model:{value:e.isPass,callback:function(n){t.$set(e,"isPass",n)},expression:"item.isPass"}})],1)])})),t._v(" "),n("div",{staticStyle:{clear:"both",padding:"40px",width:"100%"}},[n("el-row",[n("el-col",{attrs:{span:12}},[n("el-button",{staticStyle:{width:"90%"},attrs:{type:"primary"},on:{click:t.firstshenhe}},[t._v("移交国土审核")])],1),t._v(" "),n("el-col",{attrs:{span:12}},[n("el-button",{staticStyle:{width:"90%"},attrs:{type:"primary"},on:{click:t.endshenhe}},[t._v("提交审核数据")])],1)],1)],1),t._v(" "),n("el-dialog",{attrs:{visible:t.zsDialog,title:"提示",width:"30%",center:""},on:{"update:visible":function(e){t.zsDialog=e}}},[n("span",[t._v("确认审核通过吗？")]),t._v(" "),n("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{on:{click:function(e){t.confirmmethod(!1)}}},[t._v("取 消")]),t._v(" "),n("el-button",{attrs:{type:"primary"},on:{click:function(e){t.confirmmethod(!0)}}},[t._v("确 定")])],1)]),t._v(" "),n("el-dialog",{attrs:{visible:t.firstDialog,title:"提示",width:"30%",center:""},on:{"update:visible":function(e){t.firstDialog=e}}},[n("span",[t._v("确认移交国土审核吗？")]),t._v(" "),n("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{on:{click:function(e){t.gtconfirmmethod(!1)}}},[t._v("取 消")]),t._v(" "),n("el-button",{attrs:{type:"primary"},on:{click:function(e){t.gtconfirmmethod(!0)}}},[t._v("确 定")])],1)])],1)},[],!1,null,"62b2a060",null);o.options.__file="applyinfofile.vue";e.default=o.exports},AxDu:function(t,e,n){"use strict";n.d(e,"e",function(){return a}),n.d(e,"f",function(){return s}),n.d(e,"g",function(){return o}),n.d(e,"d",function(){return r}),n.d(e,"j",function(){return l}),n.d(e,"k",function(){return c}),n.d(e,"b",function(){return u}),n.d(e,"a",function(){return d}),n.d(e,"c",function(){return f}),n.d(e,"h",function(){return p}),n.d(e,"i",function(){return h});var i=n("t3Un");function a(t){return Object(i.a)({url:"/exam/applyInfoList",method:"post",data:t})}function s(t){return Object(i.a)({url:"/exam/applyProcess",method:"post",data:t})}function o(t){return Object(i.a)({url:"/exam/transfer",method:"post",data:t})}function r(t){return Object(i.a)({url:"/exam/passApplyExam",method:"post",data:t})}function l(t){return Object(i.a)({url:"/exam/print",method:"post",data:t})}function c(t){return Object(i.a)({url:"/exam/updateapplyinfo",method:"post",data:t})}function u(t){return Object(i.a)({url:"/exam/deleteapplyinfo",method:"post",data:t})}function d(t){return Object(i.a)({url:"/exam/changepaystatus",method:"post",data:t})}function f(t){return Object(i.a)({url:"/exam/downloadzehngshu",method:"post",data:t})}function p(t){return Object(i.a)({url:"/exam/getInvoices",method:"post",data:t})}function h(t){return Object(i.a)({url:"/exam/getOneApplyInfo",method:"post",data:t})}},Rs8F:function(t,e,n){"use strict";var i=n("h5U8");n.n(i).a},h5U8:function(t,e,n){}}]);