package cn.iocoder.dong.framework.captcha.core.service.impl;



import cn.iocoder.dong.framework.captcha.core.model.common.CaptchaTypeEnum;
import cn.iocoder.dong.framework.captcha.core.model.common.RepCodeEnum;
import cn.iocoder.dong.framework.captcha.core.model.common.ResponseModel;
import cn.iocoder.dong.framework.captcha.core.model.vo.CaptchaVO;
import cn.iocoder.dong.framework.captcha.core.service.CaptchaCacheService;
import cn.iocoder.dong.framework.captcha.core.util.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.Properties;

/**
 * 旋转验证码
 *
 * @author: chenkening
 * @createTime: 2023-06-04
 */
public class RotatePuzzleCaptchaServiceImpl extends AbstractCaptchaService {
    @Override
    public String captchaType() {
        return CaptchaTypeEnum.ROTATEPUZZLE.getCodeValue();
    }

    @Override
    public void init(Properties config) {
        super.init(config);
    }

    @Override
    protected CaptchaCacheService getCacheService(String cacheType) {
        return super.getCacheService(cacheType);
    }

    @Override
    public void destroy(Properties config) {
        logger.info("start-clear-history-data-", captchaType());
    }

    @Override
    public ResponseModel get(CaptchaVO captchaVO) {
        ResponseModel r = super.get(captchaVO);
        if (!validatedReq(r)) {
            return r;
        }
        //原生图片
        BufferedImage rotateImage = ImageUtils.getRotate();
        if (null == rotateImage) {
            logger.error("旋转拼图底图未初始化成功，请检查路径");
            return ResponseModel.errorMsg(RepCodeEnum.API_CAPTCHA_BASEMAP_NULL);
        }

        //抠图图片
        String rotateBlockImageBase64 = ImageUtils.getRotateBlock();
        BufferedImage rotateBlockImage = ImageUtils.getBase64StrToImage(rotateBlockImageBase64);
        if (null == rotateBlockImage) {
            logger.error("旋转拼图旋转块底图未初始化成功，请检查路径");
            return ResponseModel.errorMsg(RepCodeEnum.API_CAPTCHA_BASEMAP_NULL);
        }

        return super.get(captchaVO);
    }

    @Override
    public ResponseModel check(CaptchaVO captchaVO) {
        ResponseModel r = super.check(captchaVO);
        if (!validatedReq(r)) {
            return r;
        }

        return super.check(captchaVO);
    }
}
