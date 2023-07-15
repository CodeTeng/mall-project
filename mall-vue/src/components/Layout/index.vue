<template>
  <div class="common-layout">
    <el-container>
      <el-header><Header /></el-header>

      <el-main><router-view /></el-main>

      <el-footer><Footer /></el-footer>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import Footer from "./Footer/index.vue";
import Header from "./Header/index.vue";
</script>

<style lang="scss" scoped>
.common-layout {
  width: 100%;

  ::v-deep .el-header,
  .el-main,
  .el-footer {
    --el-header-padding: 0;
  }

  ::v-deep .el-main {
    --el-main-padding: 0;
  }

  ::v-deep .el-footer {
    --el-footer-padding: 0;
  }
}
</style>
