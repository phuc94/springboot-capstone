import { useForm, Create } from "@refinedev/antd";

import { Form, Input, Select } from "antd";

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
      </Form>
    </Create>
  );
};

enum SaleStatus {
  ACTIVE = "ACTIVE",
  DISABLE = "DISABLE",
  EXPIRED = "EXPIRED"
}

