package br.ufu.facom.apl.gui.components.singleton;

import br.ufu.facom.apl.core.ActiveLearningStrategy;
import br.ufu.facom.apl.core.MetaCategorizer;
import br.ufu.facom.apl.core.interceptor.Interceptable;
import br.ufu.facom.apl.gui.persistence.Persistent;
import br.ufu.facom.apl.gui.persistence.XMLConfiguration;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VariationPanel extends JPanel implements Persistent {

    private static VariationPanel instance;

    private final JRadioButton rbUnique;
    private final JRadioButton rbMultiple;
    private final JPanel pnlVariableParameter;
    private final JPanel pnlVariationBorder;
    private final ButtonGroup bgRadioParameter;
    private final JSpinner spinnerIncrement;
    private final JSpinner spinnerTimes;
    private final List<JRadioButton> radioButtonInterceptableParameters;
    private final List<JRadioButton> radioButtonMetaCategorizerParameters;
    private final List<JRadioButton> radioButtonActiveLearningStrategyParameters;

    private VariationPanel() {

        this.radioButtonInterceptableParameters = new ArrayList<>();
        this.radioButtonMetaCategorizerParameters = new ArrayList<>();
        this.radioButtonActiveLearningStrategyParameters = new ArrayList<>();
        this.rbUnique = new JRadioButton("Unique");
        this.rbMultiple = new JRadioButton("Multiple");
        this.pnlVariableParameter = new JPanel(new GridBagLayout());
        this.pnlVariationBorder = new JPanel(new GridBagLayout());
        this.bgRadioParameter = new ButtonGroup();
        this.spinnerIncrement = new JSpinner(new SpinnerNumberModel(0.0, -Double.MAX_VALUE, Double.MAX_VALUE,0.1));
        this.spinnerTimes = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE,1));

        ((JSpinner.NumberEditor) spinnerIncrement.getEditor()).getTextField().setColumns(1);
        ((JSpinner.NumberEditor) spinnerTimes.getEditor()).getTextField().setColumns(1);

        this.setLayout(new GridBagLayout());

        final GridBagConstraints c = new GridBagConstraints();

        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Execution");

        final JPanel pnlExecution = new JPanel(new GridBagLayout());
        pnlExecution.setBorder(border);

        border = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Variation");

        pnlVariationBorder.setBorder(border);

        final ButtonGroup bgRadioExecutionType = new ButtonGroup();
        bgRadioExecutionType.add(this.rbUnique);
        bgRadioExecutionType.add(this.rbMultiple);

        final JPanel pnlExecutionType = new JPanel(new GridBagLayout());

        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0, 20, 0, 0);
        pnlExecutionType.add(this.rbUnique, c);

        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0, 20, 0, 0);
        pnlExecutionType.add(this.rbMultiple, c);

        c.weightx = 1;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(10, 0, 10, 0);
        pnlExecution.add(pnlExecutionType, c);

        c.weightx = 1;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 0, 0, 0);
        this.add(pnlExecution, c);

        border = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Variable parameter");

        this.pnlVariableParameter.setBorder(border);

        c.weightx = 0.666;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 5, 5, 5);
        pnlExecution.add(this.pnlVariableParameter, c);

        final JLabel lblIncrement = new JLabel("Increment:");
        lblIncrement.setFont(lblIncrement.getFont().deriveFont(Font.PLAIN));

        final JLabel lblTimes = new JLabel("Times:");
        lblTimes.setFont(lblTimes.getFont().deriveFont(Font.PLAIN));

        final JPanel pnlVariation = new JPanel(new GridBagLayout());

        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(2, 2, 2, 2);
        pnlVariation.add(lblIncrement, c);

        c.weightx = 1;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(2, 2, 2, 2);
        pnlVariation.add(this.spinnerIncrement, c);

        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(2, 2, 2, 2);
        pnlVariation.add(lblTimes, c);

        c.weightx = 1;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(2, 2, 2, 2);
        pnlVariation.add(this.spinnerTimes, c);

        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 0, 0, 0);
        pnlVariationBorder.add(pnlVariation, c);

        c.weightx = 0.333;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 5, 5, 5);
        pnlExecution.add(pnlVariationBorder, c);

        this.pnlVariableParameter.setVisible(false);
        this.pnlVariationBorder.setVisible(false);
        this.setVisible(false);
        this.rbUnique.setSelected(true);

        this.configureBehavior();
    }

    public static VariationPanel getInstance() {

        if (instance == null) {
            instance = new VariationPanel();
        }
        return instance;

    }

    public void setVariableParametersList() {

        this.pnlVariableParameter.removeAll();

        this.radioButtonInterceptableParameters.forEach(this.bgRadioParameter::remove);
        this.radioButtonInterceptableParameters.clear();
        this.radioButtonMetaCategorizerParameters.forEach(this.bgRadioParameter::remove);
        this.radioButtonMetaCategorizerParameters.clear();
        this.radioButtonActiveLearningStrategyParameters.forEach(this.bgRadioParameter::remove);
        this.radioButtonActiveLearningStrategyParameters.clear();

        final List<String> interceptableParametersNames = new ArrayList<>(ConfigurationPanel.getInstance()
                .getInterceptableConfigurator().getNumericParameterValueByName().keySet());

        final List<String> metaCategorizerParametersNames = new ArrayList<>(ConfigurationPanel.getInstance()
                .getMetaCategorizerConfigurator().getNumericParameterValueByName().keySet());

        final List<String> activeLearningStrategyParametersNames = new ArrayList<>(ConfigurationPanel.getInstance()
                .getActiveLearningStrategyConfigurator().getNumericParameterValueByName().keySet());

        final GridBagConstraints c = new GridBagConstraints();

        c.weightx = 1;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(10, 0, 0, 0);
        this.pnlVariableParameter.add(
                setVariableParametersList(interceptableParametersNames, Interceptable.class), c);

        c.weightx = 1;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(10, 0, 0, 0);
        this.pnlVariableParameter.add(
                setVariableParametersList(metaCategorizerParametersNames, MetaCategorizer.class), c);

        c.weightx = 1;
        c.weighty = 0;
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(10, 0, 0, 0);
        this.pnlVariableParameter.add(
                setVariableParametersList(activeLearningStrategyParametersNames, ActiveLearningStrategy.class), c);

        if (!this.radioButtonInterceptableParameters.isEmpty()) {
            this.radioButtonInterceptableParameters.get(0).setSelected(true);
        } else if (!this.radioButtonMetaCategorizerParameters.isEmpty()) {
            this.radioButtonMetaCategorizerParameters.get(0).setSelected(true);
        } else if (!this.radioButtonActiveLearningStrategyParameters.isEmpty()) {
            this.radioButtonActiveLearningStrategyParameters.get(0).setSelected(true);
        }

    }

    private JPanel setVariableParametersList(final List<String> parametersNames, final Class<?> clazz) {

        final GridBagConstraints c = new GridBagConstraints();
        final JPanel pnlRadioSection = new JPanel(new GridBagLayout());

        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), clazz.getSimpleName());

        pnlRadioSection.setBorder(border);

        for (int i = 0; i < parametersNames.size(); i++) {

            final JRadioButton radio = new JRadioButton(parametersNames.get(i));
            radio.setFont(radio.getFont().deriveFont(Font.PLAIN));

            this.bgRadioParameter.add(radio);

            if (clazz.equals(Interceptable.class)) {
                this.radioButtonInterceptableParameters.add(radio);
            } else if (clazz.equals(MetaCategorizer.class)) {
                this.radioButtonMetaCategorizerParameters.add(radio);
            } else {
                this.radioButtonActiveLearningStrategyParameters.add(radio);
            }

            c.weightx = 0;
            c.weighty = 0;
            c.gridx = 0;
            c.gridy = i;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.anchor = GridBagConstraints.WEST;
            c.fill = GridBagConstraints.NONE;
            c.insets = new Insets(0, 0, 0, 0);
            pnlRadioSection.add(radio, c);

        }

        pnlRadioSection.setVisible(!parametersNames.isEmpty());

        return pnlRadioSection;

    }

    @Override
    public void reset() {

        this.setVisible(false);
        this.pnlVariableParameter.setVisible(false);
        this.pnlVariationBorder.setVisible(false);
        this.rbUnique.setSelected(true);
        this.rbMultiple.setSelected(false);
        this.radioButtonInterceptableParameters.clear();
        this.radioButtonMetaCategorizerParameters.clear();
        this.radioButtonActiveLearningStrategyParameters.clear();
        this.spinnerIncrement.setValue(0);
        this.spinnerTimes.setValue(0);

    }

    @Override
    public void load(XMLConfiguration configuration) {

        this.rbUnique.setSelected(!configuration.isMultipleExecutions());
        this.rbMultiple.setSelected(configuration.isMultipleExecutions());
        this.pnlVariableParameter.setVisible(configuration.isMultipleExecutions());
        this.pnlVariationBorder.setVisible(configuration.isMultipleExecutions());

        final List<JRadioButton> radioButtons;

        if (Interceptable.class.getSimpleName().equals(configuration.getVariableParameterType())) {
            radioButtons = radioButtonInterceptableParameters;
        } else if (MetaCategorizer.class.getSimpleName().equals(configuration.getVariableParameterType())) {
            radioButtons = radioButtonMetaCategorizerParameters;
        } else {
            radioButtons = radioButtonActiveLearningStrategyParameters;
        }

        radioButtons.stream()
                .filter(radioButton -> radioButton.getText().equals(configuration.getVariableParameterName()))
                .findAny()
                .ifPresent(radioButton -> radioButton.setSelected(true));

        this.spinnerIncrement.setValue(configuration.getVariationIncrement());
        this.spinnerTimes.setValue(configuration.getVariationTimes());

    }

    @Override
    public void save(XMLConfiguration configuration) {

        configuration.setMultipleExecutions(this.rbMultiple.isSelected());

        this.radioButtonInterceptableParameters.stream()
                .filter(AbstractButton::isSelected).findAny().ifPresent((btnRadio) -> {
                    configuration.setVariableParameterName(btnRadio.getText());
                    configuration.setVariableParameterType(Interceptable.class.getSimpleName());
                });

        this.radioButtonMetaCategorizerParameters.stream()
                .filter(AbstractButton::isSelected).findAny().ifPresent((btnRadio) -> {
                    configuration.setVariableParameterName(btnRadio.getText());
                    configuration.setVariableParameterType(MetaCategorizer.class.getSimpleName());
                });

        this.radioButtonActiveLearningStrategyParameters.stream()
                .filter(AbstractButton::isSelected).findAny().ifPresent((btnRadio) -> {
                    configuration.setVariableParameterName(btnRadio.getText());
                    configuration.setVariableParameterType(ActiveLearningStrategy.class.getSimpleName());
                });

        try {
            this.spinnerIncrement.commitEdit();
        } catch (Exception e) {

            final String message = "Invalid value for Variation's 'increment' field. Reverting to previous value."
                    + "\n    " + e.getMessage() + "\n    " + ExceptionUtils.getRootCauseMessage(e);

            JOptionPane.showMessageDialog(GUI.getInstance(), message,
                    "Error", JOptionPane.ERROR_MESSAGE);

            this.spinnerIncrement.setValue(this.spinnerIncrement.getPreviousValue());
        }

        try {
            this.spinnerTimes.commitEdit();
        } catch (Exception e) {

            final String message = "Invalid value for Variation's 'times' field. Reverting to previous value."
                    + "\n    " + e.getMessage() + "\n    " + ExceptionUtils.getRootCauseMessage(e);
            JOptionPane.showMessageDialog(GUI.getInstance(), message,
                    "Error", JOptionPane.ERROR_MESSAGE);

            this.spinnerTimes.setValue(this.spinnerTimes.getPreviousValue());

        }

        configuration.setVariationIncrement((Double) this.spinnerIncrement.getValue());
        configuration.setVariationTimes((Integer) this.spinnerTimes.getValue());

    }

    private void configureBehavior() {

        final ActionListener typeExecutionChange = (e) -> {
            this.pnlVariableParameter.setVisible(!this.rbUnique.isSelected());
            this.pnlVariationBorder.setVisible(!this.rbUnique.isSelected());
        };

        this.rbUnique.addActionListener(typeExecutionChange);
        this.rbMultiple.addActionListener(typeExecutionChange);
    }
}
