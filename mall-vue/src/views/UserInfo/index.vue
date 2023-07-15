<template>
  <Search title="个人中心" :isShowLi="false" :isShowSearch="false" />
  <div class="card-main">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>个人资料</span>
          <el-button class="button" @click="submitForm(ruleFormRef)" text
            >更新信息</el-button
          >
        </div>
      </template>
      <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        class="el-form-main"
      >
        <!-- 设置用户头像 -->
        <el-form-item
          class="form-item"
          label="用户头像："
          prop="userProfilePictureSrc"
        >
          <el-upload
            name="file"
            class="avatar-uploader"
            accept=".jpg, .jpeg, .png"
            :show-file-list="false"
            :http-request="uploadFile"
            :limit="1"
          >
            <el-avatar
              v-if="ruleForm.userProfilePictureSrc"
              :src="ruleForm.userProfilePictureSrc"
              shape="square"
              :size="100"
            />
            <el-icon v-else class="avatar-uploader-icon">
              <Plus />
            </el-icon>
          </el-upload>
        </el-form-item>

        <!-- 昵称 -->
        <el-form-item class="form-item" label="用户昵称:" prop="userNickName">
          <el-input
            name="nickName"
            v-model="ruleForm.userNickName"
            placeholder="请输入昵称"
            maxlength="20"
            type="text"
            class="el-input-item"
          />
        </el-form-item>

        <!-- 真实姓名-->
        <el-form-item class="form-item" label="真实姓名:" prop="userRealname">
          <el-input
            name="userRealname"
            v-model="ruleForm.userRealname"
            placeholder="请输入真实姓名"
            maxlength="20"
            type="text"
            class="el-input-item"
          />
        </el-form-item>

        <!-- 设置密码 -->
        <el-form-item class="form-item" label="输入密码:" prop="userPassword">
          <el-input
            name="password"
            type="password"
            v-model="ruleForm.userPassword"
            placeholder="请设置登录密码"
            show-password
            class="el-input-item"
          />
        </el-form-item>
        <!-- 确认密码 -->
        <el-form-item
          class="form-item"
          label="确认密码:"
          prop="confirmPassword"
        >
          <el-input
            name="confirmPassword"
            type="password"
            v-model="ruleForm.confirmPassword"
            placeholder="请再次输入你的密码"
            show-password
            maxlength="20"
            class="el-input-item"
          />
        </el-form-item>

        <!-- 性别选取 -->
        <el-form-item class="form-item" label="用户性别：" prop="userGender">
          <el-select v-model="ruleForm.userGender" clearable filterable>
            <el-option label="男" :value="0"></el-option>
            <el-option label="女" :value="1"></el-option>
          </el-select>
        </el-form-item>
        <!-- 出生日期 -->
        <el-form-item class="form-item" label="出生日期：" prop="userBirthday">
          <el-date-picker
            v-model="ruleForm.userBirthday"
            type="date"
            placeholder="请选择时间"
            value-format="YYYY-MM-DD"
          />
        </el-form-item>

        <!-- 地址选取 -->
        <el-form-item class="form-item" label="居住地址:" prop="userAddress">
          <el-select
            v-model="ruleForm.userProvince"
            filterable
            placeholder="省"
            class="el-select-item"
            @change="proChange()"
          >
            <el-option
              v-for="item in provinceAddressOptions"
              :key="item.addressAreaId"
              :label="item.addressName"
              :value="item.addressAreaId"
            />
          </el-select>
          <!-- 市 -->
          <el-select
            v-model="ruleForm.userAddress"
            filterable
            placeholder="市"
            class="el-select-item"
            @change="cityChange()"
          >
            <el-option
              v-for="item in cityAddressOptions"
              :key="item.addressAreaId"
              :label="item.addressName"
              :value="item.addressAreaId"
            />
          </el-select>
          <!-- 区 -->
          <el-select
            v-model="ruleForm.userHomePlace"
            filterable
            placeholder="区"
            class="el-select-item"
          >
            <el-option
              v-for="item in regionAddressOptions"
              :key="item.addressAreaId"
              :label="item.addressName"
              :value="item.addressAreaId"
            />
          </el-select>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from "vue";
import { updateUser } from "../../api/user";
import { uploadFileApi } from "../../api/oss";
import { Plus } from "@element-plus/icons-vue";
import { useUserStore } from "../../store";
import { getAddressApi, getProvinceApi } from "../../api/address";
const userStore = useUserStore();

const ruleFormRef = ref();

const rules = reactive<any>({
  userName: [{ required: true, message: "输入不能为空", trigger: "blur" }],
  userPassword: [{ required: true, message: "输入不能为空", trigger: "blur" }],
  confirmPassword: [
    { required: true, message: "输入不能为空", trigger: "blur" },
  ],
  userRealname: [{ required: true, message: "输入不能为空", trigger: "blur" }],
  userNickName: [{ required: true, message: "输入不能为空", trigger: "blur" }],
  userGender: [{ required: true, message: "输入不能为空", trigger: "blur" }],
  userBirthday: [{ required: true, message: "输入不能为空", trigger: "blur" }],
  userAddress: [{ required: true, message: "输入不能为空", trigger: "blur" }],
  userHomePlace: [{ required: true, message: "输入不能为空", trigger: "blur" }],
});

const ruleForm = ref<UpdateUserDTO>({
  userAddress: "",
  userBirthday: "",
  userGender: 0,
  userHomePlace: "",
  userName: "",
  userNickName: "",
  userProfilePictureSrc: "",
  userRealname: "",
  confirmPassword: "",
  userPassword: "",
  userProvince: "",
});

// 获得用户得数据
userStore.getInfo().then((res) => {
  console.log(res);
  if (res.code === 0) {
    ruleForm.value = res.data;
  }
  proChange();
  cityChange();
});

// 获得省列表
const provinceAddressOptions = ref<any>();
getProvinceApi().then((res) => {
  if (res.code == 0) {
    provinceAddressOptions.value = res.data;
  }
});

// 获得市一级列表
const proChange = () => {
  getAddressApi(ruleForm.value.userProvince).then((res) => {
    if (res.code === 0) {
      cityAddressOptions.value = res.data;
    } else {
      ElMessage.error("获取市区失败");
    }
  });
};

// 市
const cityAddressOptions = ref<AddressDTO[]>([]);

// 获得区一级列表
const cityChange = () => {
  getAddressApi(ruleForm.value.userAddress).then((res) => {
    if (res.code === 0) {
      regionAddressOptions.value = res.data;
    } else {
      ElMessage.error("获取地区失败");
    }
  });
};

// 区
const regionAddressOptions = ref<AddressDTO[]>([]);

const uploadFile = (item: any) => {
  const formData = new FormData();
  formData.append("file", item.file);
  uploadFileApi(formData).then((res) => {
    if (res.data.code === 0) {
      ElMessage.success("上传成功");
      ruleForm.value.userProfilePictureSrc = res.data.data;
    } else {
      ElMessage.error("上传数据失败" + res.data.mesage);
    }
  });
};

// 提交表单
const submitForm = async (formEl: any) => {
  if (!formEl) return;
  await formEl.validate((valid: any, fields: any) => {
    if (valid) {
      updateUser(ruleForm.value)
        .then((res) => {
          if (res.code === 0) {
            ElMessage.success("更新成功");
          } else {
            ElMessage.success(res.message);
          }
        })
        .catch((e) => {
          ElMessage.success(e.message);
        });
    } else {
      console.log("提交出错", fields);
    }
  });
};
</script>

<style lang="scss" scoped>
.el-select-item {
  width: 95px;
  margin-right: 5px;
}

.el-form-main {
  width: 600px;
  margin-left: auto;
  margin-right: auto;
}

.box-card {
  width: 1000px;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 1000px;
}
</style>

<!-- 头像样式 -->
<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
:deep .el-upload__input {
  display: none;
}
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>
