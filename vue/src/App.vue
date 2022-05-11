<template>
  <div id="app">
    <div id="nav">
      <!-- <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp; -->
      <!-- <router-link v-bind:to="{ name: 'itineraries' }">Itineraries</router-link>&nbsp;|&nbsp; --> 
      <!-- <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link> -->
    </div>
    <router-view />
  </div>
</template>
<style>
  html,body{
    display: flex;
    flex-direction: column;
    width: auto;
    margin: 0;
    padding: 0;
    overflow-x: hidden;
    min-width: 320px;
    background: #fff;
    font-family: Lato,'Helvetica Neue',Arial,Helvetica,sans-serif;
    font-size: 14px;
    line-height: 1.4285em;
    color: rgba(0,0,0,.87);
 }
 #app{
   display: flex;
   flex-direction: column;
   margin: 0px;
   height: 100%;
 }
</style>