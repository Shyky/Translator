package com.shyky.java.plugin.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;
import com.shyky.java.plugin.bean.YouDaoChineseTranslateResult;
import com.shyky.java.plugin.bean.YouDaoEnglishTranslateResult;
import com.shyky.java.plugin.constant.Settings;
import com.shyky.java.plugin.service.TranslateService;
import com.shyky.java.plugin.service.YouDaoChineseTransformService;
import com.shyky.java.plugin.service.YouDaoEnglishTransformService;
import com.shyky.java.plugin.util.TextUtil;
import org.apache.http.util.TextUtils;

import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TranslateAction extends AnAction {
    private TranslateService translateService;
    //    private TransformService transformService;
    private YouDaoChineseTransformService youDaoChineseTransformService;
    private YouDaoEnglishTransformService youDaoEnglishTransformService;

    public TranslateAction() {
        translateService = new TranslateService();
//        transformService = new TransformService();
        youDaoChineseTransformService = new YouDaoChineseTransformService();
        youDaoEnglishTransformService = new YouDaoEnglishTransformService();
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (editor != null) {
            SelectionModel selectionModel = editor.getSelectionModel();
            final String selectedEditorText = selectionModel.getSelectedText();
            if (selectedEditorText != null && !TextUtils.isEmpty(selectedEditorText.trim())) {
                if (TextUtil.isChinese(selectedEditorText)) {
                    // Chinese need url encode,use the specified encoding utf-8.
                    try {
                        translateService.translate(URLEncoder.encode(selectedEditorText, "utf-8"),
                                new TranslateService.Callback<YouDaoChineseTranslateResult>(YouDaoChineseTranslateResult.class) {
                                    @Override
                                    public void onSuccess(YouDaoChineseTranslateResult translateResult, String response) {
                                        final String htmlResult = youDaoChineseTransformService.transform(selectedEditorText, translateResult);
                                        JBPopupFactory popupFactory = JBPopupFactory.getInstance();
                                        popupFactory.createHtmlTextBalloonBuilder(htmlResult, null,
                                                new JBColor(new Color(186, 233, 18), new Color(73, 117, 73)),
                                                null)
                                                .setFadeoutTime(Settings.POPUP_BALLOON_FADEOUT_TIME).createBalloon()
                                                .show(popupFactory.guessBestPopupLocation(editor), Balloon.Position.below);
                                    }

                                    @Override
                                    public void onFailure(Throwable error, String message) {

                                    }
                                });
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                } else if (TextUtil.isEnglish(selectedEditorText)) {
                    translateService.translate(selectedEditorText, new TranslateService.Callback<YouDaoEnglishTranslateResult>(YouDaoEnglishTranslateResult.class) {
                        @Override
                        public void onSuccess(YouDaoEnglishTranslateResult translateResult, String response) {
                            final String htmlResult = youDaoEnglishTransformService.transform(selectedEditorText, translateResult);
                            JBPopupFactory popupFactory = JBPopupFactory.getInstance();
                            popupFactory.createHtmlTextBalloonBuilder(htmlResult, null,
                                    new JBColor(new Color(186, 233, 18), new Color(73, 117, 73)),
                                    null)
                                    .setFadeoutTime(Settings.POPUP_BALLOON_FADEOUT_TIME).createBalloon()
                                    .show(popupFactory.guessBestPopupLocation(editor), Balloon.Position.below);
                        }

                        @Override
                        public void onFailure(Throwable error, String message) {

                        }
                    });
                }
            } else {
//                selectionModel.get
            }
        }
    }
}