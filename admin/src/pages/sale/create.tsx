import { useForm, Create } from "@refinedev/antd";

import { DatePicker, Form, Input, Select } from "antd";

export const CreateSale= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  return (
    <Create saveButtonProps={saveButtonProps}>
      <Form {...formProps} layout="vertical">
        <Form.Item label="Amount" name="amount">
          <Input />
        </Form.Item>
        <Form.Item label="Status" name="status">
          <Select options={
              Object.keys(SaleStatus).map(key => {return {value: key, label: key}})
            }
          />
        </Form.Item>
        <Form.Item label="Start Date" name="startDate">
          <DatePicker />
        </Form.Item>
        <Form.Item label="End Date" name="endDate">
          <DatePicker />
        </Form.Item>
      </Form>
    </Create>
  );
};

enum SaleStatus {
  ACTIVE = "ACTIVE",
  DISABLE = "DISABLE",
  EXPIRED = "EXPIRED"
}

