package demo.web.ui.components;

public class ResultRowOptions {
    public String generateText = "Сгенерировать";
    public String resultFieldWidth = null;
    public String generateButtonWidth = null;

    public static ResultRowOptions defaults() {
        return new ResultRowOptions();
    }

    public ResultRowOptions copy() {
        var o = new ResultRowOptions();
        o.resultFieldWidth = this.resultFieldWidth;
        o.generateButtonWidth = this.generateButtonWidth;
        o.generateText = this.generateText;
        return o;
    }

}
