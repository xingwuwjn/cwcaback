(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-7c0b"],{aho1:function(n,t,o){},c11S:function(n,t,o){"use strict";var e=o("gTgX");o.n(e).a},gTgX:function(n,t,o){},no6p:function(n,t,o){"use strict";var e=o("aho1");o.n(e).a},ntYl:function(n,t,o){"use strict";o.r(t);var e={name:"Login",components:{LangSelect:o("ETGp").a},data:function(){return{loginForm:{username:"admin",password:""},loginRules:{password:[{required:!0,trigger:"blur"}]},passwordType:"password",loading:!1,showDialog:!1,redirect:void 0}},watch:{$route:{handler:function(n){this.redirect=n.query&&n.query.redirect},immediate:!0}},created:function(){},destroyed:function(){},methods:{showPwd:function(){"password"===this.passwordType?this.passwordType="":this.passwordType="password"},handleLogin:function(){var n=this;this.$refs.loginForm.validate(function(t){if(!t)return!1;n.loading=!0,n.$store.dispatch("LoginByUsername",n.loginForm).then(function(){n.loading=!1,n.$router.push("/dashboard")}).catch(function(t){n.loading=!1})})}}},s=(o("c11S"),o("no6p"),o("KHd+")),a=Object(s.a)(e,function(){var n=this,t=n.$createElement,o=n._self._c||t;return o("div",{staticClass:"login-container"},[o("el-form",{ref:"loginForm",staticClass:"login-form",attrs:{model:n.loginForm,rules:n.loginRules,"auto-complete":"on","label-position":"left"}},[o("div",{staticClass:"title-container"},[o("h3",{staticClass:"title"},[n._v("西部CA数字证书在线自助办理系统（土地与矿业权交易）")]),n._v(" "),o("lang-select",{staticClass:"set-language"})],1),n._v(" "),o("el-form-item",{attrs:{prop:"username"}},[o("span",{staticClass:"svg-container"},[o("svg-icon",{attrs:{"icon-class":"user"}})],1),n._v(" "),o("el-input",{attrs:{placeholder:n.$t("login.username"),name:"username",type:"text","auto-complete":"on"},model:{value:n.loginForm.username,callback:function(t){n.$set(n.loginForm,"username",t)},expression:"loginForm.username"}})],1),n._v(" "),o("el-form-item",{attrs:{prop:"password"}},[o("span",{staticClass:"svg-container"},[o("svg-icon",{attrs:{"icon-class":"password"}})],1),n._v(" "),o("el-input",{attrs:{type:n.passwordType,placeholder:n.$t("login.password"),name:"password","auto-complete":"on"},nativeOn:{keyup:function(t){return"button"in t||!n._k(t.keyCode,"enter",13,t.key,"Enter")?n.handleLogin(t):null}},model:{value:n.loginForm.password,callback:function(t){n.$set(n.loginForm,"password",t)},expression:"loginForm.password"}}),n._v(" "),o("span",{staticClass:"show-pwd",on:{click:n.showPwd}},[o("svg-icon",{attrs:{"icon-class":"eye"}})],1)],1),n._v(" "),o("el-button",{staticStyle:{width:"100%","margin-bottom":"30px"},attrs:{loading:n.loading,type:"primary"},nativeOn:{click:function(t){return t.preventDefault(),n.handleLogin(t)}}},[n._v(n._s(n.$t("login.logIn")))])],1)],1)},[],!1,null,"39a572d2",null);a.options.__file="index.vue";t.default=a.exports}}]);