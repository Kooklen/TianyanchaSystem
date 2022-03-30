<template>
  <div class="main">
    <br>
    <el-container style="margin: 0">
      <el-container>

        <div class="main_background">
          <div id="asidecolor" :class="{'is_fixed' : isFixed}">
            <el-aside
            >
              <div
                  v-for="anchor in titles"
                  :style="{  padding: `10px 0 10px ${anchor.indent * 20}px`, fontWeight:anchor.indent==0?'900' :anchor.indent==1?'800' :anchor.indent==2?'700':'unset', fontSize:anchor.indent==0?'20px' :anchor.indent==1?'17px' :anchor.indent==2?'15px':'unset'   }"
                  @click="handleAnchorClick(anchor)"
              >
                <a style="cursor: pointer">{{ anchor.title }}</a>
              </div>
            </el-aside>
          </div>
          <div>
            <el-main>
              <div style="width: 800px;">
                <v-md-preview :text="text" ref="preview"/>
              </div>
            </el-main>
          </div>
        </div>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import README from "../../public/README.md"

export default {
  data() {
    return {
      text: README,
      titles: [],
      isFixed: false,
      offsetTop: 0,
    };
  },
  mounted() {
    window.addEventListener('scroll', this.initHeight);
    this.$nextTick(() => {
      //获取对象相对于版面或由 offsetTop 属性指定的父坐标的计算顶端位置
      this.offsetTop = document.querySelector('#boxFixed').offsetTop;
    })

    const anchors = this.$refs.preview.$el.querySelectorAll('h1,h2,h3,h4,h5,h6');
    const titles = Array.from(anchors).filter((title) => !!title.innerText.trim());

    if (!titles.length) {
      this.titles = [];
      return;
    }

    const hTags = Array.from(new Set(titles.map((title) => title.tagName))).sort();

    this.titles = titles.map((el) => ({
      title: el.innerText,
      lineIndex: el.getAttribute('data-v-md-line'),
      indent: hTags.indexOf(el.tagName),
    }));
  },
  destroyed() {
    window.removeEventListener('scroll', this.handleScroll)
  },
  methods: {
    initHeight() {
// 设置或获取位于对象最顶端和窗口中可见内容的最顶端之间的距离 (被卷曲的高度)
      var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
//如果被卷曲的高度大于吸顶元素到顶端位置 的距离
      this.isFixed = scrollTop > this.offsetTop ? true : false;
    },
    handleAnchorClick(anchor) {
      const {preview} = this.$refs;
      const {lineIndex} = anchor;

      const heading = preview.$el.querySelector(`[data-v-md-line="${lineIndex}"]`);

      if (heading) {
        preview.scrollToTarget({
          target: heading,
          scrollContainer: window,
          top: 60,
        });
      }
    },
  },
};
</script>


<style lang="less" scoped>


/deep/ .el-checkbox {
  margin-right: 10px;
}

/deep/ .el-form-item__label {
  padding-left: 50px;
}

/deep/ .el-form-item {
  margin-bottom: 20px;
}

.setting1 {
  /deep/ .el-input__inner {
    width: 260px;
  }
}

.left {
  width: 100%;
  background-image: url("../img/bc_img_no_logo.png");
}

.setting {
  span {
    width: 53px;
    height: 21px;
    font-size: 16px;
    color: #171717;
    line-height: 21px;
    margin-left: 80px;
  }

  .rules {
    margin: 16px;
    width: 260px;
    height: 43px;
  }

  /deep/ .el-col3 {
    margin-top: 2px;
  }
}

/deep/ .el-tabs__item {
  //background-color: #42b983;
}

* {
  margin: 0;
  padding: 0;
}

.undefined {
  height: 10px;
}

em {
  color: rgb(14, 104, 204);
}

.addA {
  display: flex;
  width: 98%;
}

.success {
  width: 95px;
  height: 40px;
  margin-left: auto;
}

.main {
  background-color: rgb(234, 234, 234);
  //margin-top: 20px;
  //padding-top: 30px;
  width: 100%;
  height: 100%;
}

.el-header {
  width: 1620px;
  height: 159px !important;
  background-color: white;
  margin: auto;
  display: flex;
  //border:1px solid;
}

.main_background {
  width: 1620px;
  margin: auto;
  //background-color: #42b983;
  display: flex;
  flex-direction: row;
}

#asidecolor {
  //height: 100%;
  width: 409px;
  background-color: white;
  position: fixed;
  top: 145px;
  left: 10%;
  height: 600px;

  //left: auto;
  //display: inline-block;
}

//.is_fixed {
//  width: 100%;
//  position: fixed;
//  top: 50px;
//  left: 150px;
//  z-index: 999;
//  height: 600px;
//}

.el-aside {
  //position: fixed;
  //left: auto;
  //margin-top: 30px;
  //top: 150px;
  //font-weight: 1000;
  background-color: white;
  width: 409px !important;
  //height: 90%;
  //margin-right: 23px;
  //border: 1px solid;
  font-size: 16px;
  padding-left: 100px;

  div {
    //border-bottom: 1px solid;
    //width: 100%;
  }

  a:active {
    color: red;
  }

  div:hover {
    color: red;

  }

  a:visited {
    color: red;

  }
}

.el-main {
  width: 1190px;
  height: 100%;
  background-color: white;
  margin-left: 40%;
  position: relative;
}

is_fixed2 {
  margin-left: 70%;
}

.el-container {
  //background-color: #42b983;
  //margin: auto;
  margin-top: 10px;

}

.header_img {
  width: 85px;
  height: 89px;
  margin: auto 26px auto 57px;
}

.header_word {
  margin-top: auto;
  margin-bottom: auto;
  text-indent: 50px;
  font-size: 50px;
  color: white;
}

.right {
  margin-left: 612px;
  font-size: 24px;

  .number {
    font-size: 35px;
    text-align: center;
  }

  .module {
    width: 96px;
    height: 80px;
    margin: auto;
    margin-right: 26px;
    display: flex;
    flex-direction: column;
  }

  .el-icon-setting {
    margin: auto;
    padding-left: 20px;
    font-size: 35px;
  }

  .el-table {
    //margin-top: 20px;
  }

}
</style>
