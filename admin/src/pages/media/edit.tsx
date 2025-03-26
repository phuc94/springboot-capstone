import { useForm, Edit } from "@refinedev/antd";

import { Form, Input, Select, Switch, } from "antd";

export const EditMedia= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  return (
    <Edit saveButtonProps={saveButtonProps} >
      <Form {...formProps} layout="vertical">
        <Form.Item label="Url" name="url">
          <Input />
        </Form.Item>
        <Form.Item label="Title" name="title">
          <Input />
        </Form.Item>
        <Form.Item label="Media Type" name="media_type">
          <Select options={
              Object.keys(MediaType).map(key => {return {value: key, label: key}})
            }
          />
        </Form.Item>
        <Form.Item label="Is Primary" name="primary">
          <Switch />
        </Form.Item>
      </Form>
    </Edit>
  );
};

enum MediaType {
  IMAGE = "IMAGE",
  VIDEO = "VIDEO"
}

enum IsPrimary {
  TRUE = "true",
  FALSE = "false"
}
