<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>test</title>
  <style>

    body {
      padding: 0;
      margin: 0;
    }

    .top-container {
      height: 21px;
      width: 100%;
      display: inline-block;
    }

    .top {
      height: 21px;
      background: #fff;
      width: 100%;
    }

    .top-content,
    .bottom-content {
      height: 200vh;
      display: inline-block;
      width: 100%;
      background-color: #f1f1f1;
    }
  </style>
</head>

<body>
<div class="top-content"></div>
<div class="top-container">
  <div class="top">top</div>
</div>
<div class="bottom-content"></div>
</body>
<script>
  function fixedTop(element, option) {
    this.element = element;
    this.setting = Object.assign({}, fixedTop.defaults, option);
    this.init();
  }

  fixedTop.defaults = {
    fixed: "position: fixed;top: 0;z-index: 1000",
    none: "position: relative;z-index: 0",
  };

  fixedTop.prototype = {
    init: function () {
      const element = this.element;
      const { fixed, none, id } = this.setting;
      window.addEventListener('scroll', () => {
        const obj = document.getElementsByClassName(id)[0].getBoundingClientRect();
        if (obj.top - element.clientHeight < 0 && obj.bottom - element.clientHeight > 0) {
          element.style = fixed;
        } else {
          element.style = none;
        }
      });
    }
  }
  fixedTop.constructor = fixedTop;
  new fixedTop(document.querySelectorAll('.top-container')[0], { id: 'bottom-content' });
</script>

</html>
