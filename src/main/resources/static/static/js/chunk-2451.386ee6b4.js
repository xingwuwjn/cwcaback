(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-2451","chunk-6cec"],{BJOg:function(e,t,i){},eR8p:function(e,t,i){"use strict";i.r(t);var s=i("s8mb"),a={data:function(){return{keywords:"",fullloading:!1,hrs:[],cardLoading:[],eploading:[],allRoles:[],moreBtnState:!1,selRoles:[],selRolesBak:[]}},mounted:function(){this.initCards(),this.loadAllRoles()},methods:{searchClick:function(){this.initCards(),this.loadAllRoles()},updateHrRoles:function(e,t){this.moreBtnState=!1;var i=this;if(this.selRolesBak.length===this.selRoles.length){for(var a=0;a<this.selRoles.length;a++)for(var l=0;l<this.selRolesBak.length;l++)if(this.selRoles[a]===this.selRolesBak[l]){this.selRolesBak.splice(l,1);break}if(0===this.selRolesBak.length)return}this.eploading.splice(t,1,!0),Object(s.k)(e,this.selRoles).then(function(s){console.log("jilaile"),i.eploading.splice(t,1,!1),s&&200===s.status&&i.refreshHr(e,t)})},refreshHr:function(e,t){var i=this;i.cardLoading.splice(t,1,!0),Object(s.g)(e).then(function(e){i.cardLoading.splice(t,1,!1),i.hrs.splice(t,1,e.data)})},loadSelRoles:function(e,t){var i=this;this.moreBtnState=!0,this.selRoles=[],this.selRolesBak=[],e.forEach(function(e){i.selRoles.push(e.id),i.selRolesBak.push(e.id)})},loadAllRoles:function(){var e=this;Object(s.e)().then(function(t){e.fullloading=!1,t&&200===t.status&&(e.allRoles=t.data)})},switchChange:function(e,t,i){var a=this;a.cardLoading.splice(i,1,!0),Object(s.j)(e,t).then(function(e){(a.cardLoading.splice(i,1,!1),e&&200===e.status)?"error"===e.data.status&&a.refreshHr(t,i):a.refreshHr(t,i)})},initCards:function(){this.fullloading=!0;var e,t=this;e=""===this.keywords?"all":this.keywords,Object(s.f)(e).then(function(e){if(e&&200===e.status){t.hrs=e.data;var i=e.data.length;t.cardLoading=Array.apply(null,Array(i)).map(function(e,t){return!1}),t.eploading=Array.apply(null,Array(i)).map(function(e,t){return!1})}})},deleteHr:function(e){var t=this;this.fullloading=!0,Object(s.b)(e).then(function(e){(t.fullloading=!1,e&&200===e.status)&&("success"===e.data.status&&(t.initCards(),t.loadAllRoles()))})}}},l=(i("omly"),i("KHd+")),n=Object(l.a)(a,function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{directives:[{name:"loading",rawName:"v-loading",value:e.fullloading,expression:"fullloading"}],staticStyle:{"margin-top":"10px"}},[i("div",{staticStyle:{"margin-bottom":"10px",display:"flex","justify-content":"center","align-items":"center"}},[i("el-input",{staticStyle:{width:"400px","margin-right":"10px"},attrs:{model:e.keywords,placeholder:"默认展示部分用户，可以通过用户名搜索更多用户...","prefix-icon":"el-icon-search",size:"small"}}),e._v(" "),i("el-button",{attrs:{size:"small",type:"primary",icon:"el-icon-search"},on:{click:e.searchClick}},[e._v("搜索")])],1),e._v(" "),i("div",{staticStyle:{display:"flex","justify-content":"space-around","flex-wrap":"wrap","text-align":"left"}},e._l(e.hrs,function(t,s){return i("el-card",{directives:[{name:"loading",rawName:"v-loading",value:e.cardLoading[s],expression:"cardLoading[index]"}],key:t.id,staticStyle:{width:"350px","margin-bottom":"20px"}},[i("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[i("span",[e._v(e._s(t.name))]),e._v(" "),i("el-button",{staticStyle:{color:"#f6061b",margin:"0px",float:"right",padding:"3px 0",width:"15px",height:"15px"},attrs:{type:"text",icon:"el-icon-delete"},on:{click:function(i){e.deleteHr(t.id)}}})],1),e._v(" "),i("div",[i("div",{staticStyle:{width:"100%",display:"flex","justify-content":"center"}},[i("img",{staticStyle:{width:"70px",height:"70px","border-radius":"70px"},attrs:{src:t.userface,alt:"item.name"}})]),e._v(" "),i("div",{staticStyle:{"margin-top":"20px"}},[i("div",[i("span",{staticClass:"user-info"},[e._v("用户名:"+e._s(t.name))])]),e._v(" "),i("div",[i("span",{staticClass:"user-info"},[e._v("手机号码:"+e._s(t.phone))])]),e._v(" "),i("div",[i("span",{staticClass:"user-info"},[e._v("电话号码:"+e._s(t.telephone))])]),e._v(" "),i("div",[i("span",{staticClass:"user-info"},[e._v("地址:"+e._s(t.address))])]),e._v(" "),i("div",{staticClass:"user-info",staticStyle:{display:"flex","align-items":"center","margin-bottom":"3px"}},[e._v("\n            用户状态:\n            "),i("el-switch",{key:t.id,staticStyle:{display:"inline","margin-left":"5px"},attrs:{"active-color":"#13ce66","inactive-color":"#aaaaaa","active-text":"启用","inactive-text":"禁用"},on:{change:function(i){e.switchChange(t.enabled,t.id,s)}},model:{value:t.enabled,callback:function(i){e.$set(t,"enabled",i)},expression:"item.enabled"}})],1),e._v(" "),i("div",{staticClass:"user-info"},[e._v("\n            用户角色:\n            "),e._l(t.roles,function(t){return i("el-tag",{key:t.id,staticStyle:{"margin-right":"5px"},attrs:{"disable-transitions":!1,type:"success",size:"mini"}},[e._v(e._s(t.nameZh)+"\n            ")])}),e._v(" "),i("el-popover",{directives:[{name:"loading",rawName:"v-loading",value:e.eploading[s],expression:"eploading[index]"}],key:t.id,attrs:{trigger:"click",placement:"right",title:"角色列表",width:"200"},on:{hide:function(i){e.updateHrRoles(t.id,s)}}},[i("el-select",{attrs:{multiple:"",placeholder:"请选择角色"},model:{value:e.selRoles,callback:function(t){e.selRoles=t},expression:"selRoles"}},e._l(e.allRoles,function(e){return i("el-option",{key:e.id,attrs:{label:e.nameZh,value:e.id}})})),e._v(" "),i("el-button",{staticStyle:{color:"#09c0f6","padding-top":"0px"},attrs:{slot:"reference",disabled:e.moreBtnState,type:"text",icon:"el-icon-more"},on:{click:function(i){e.loadSelRoles(t.roles,s)}},slot:"reference"})],1)],2),e._v(" "),i("div",[i("span",{staticClass:"user-info"},[e._v("备注:"+e._s(t.remark))])])])])])}))])},[],!1,null,null,null);n.options.__file="SysUser.vue";t.default=n.exports},omly:function(e,t,i){"use strict";var s=i("BJOg");i.n(s).a},yxNK:function(e,t,i){var s={"./SysBasic.vue":"xatG","./SysUser.vue":"eR8p","./basic/MenuRole.vue":"82H3"};function a(e){var t=l(e);return i(t)}function l(e){var t=s[e];if(!(t+1)){var i=new Error("Cannot find module '"+e+"'");throw i.code="MODULE_NOT_FOUND",i}return t}a.keys=function(){return Object.keys(s)},a.resolve=l,e.exports=a,a.id="yxNK"}}]);