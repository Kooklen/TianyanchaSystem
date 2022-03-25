class RandomUtil {
}
//静态方法，获得随机数
RandomUtil.getRandomNumber = (min, max) => parseInt(Math.random() * (max - min)) + min
module.exports = RandomUtil
