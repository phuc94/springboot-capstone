import { useForm, Create } from "@refinedev/antd";
import ReactQuill from "react-quill";

import { Form, Input } from "antd";

export const CreateDescription= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  return (
    <Create saveButtonProps={saveButtonProps}>
      <Form {...formProps} layout="vertical">
        <Form.Item label="Description" name="description">
          <ReactQuill theme="snow" />
        </Form.Item>
      </Form>
    </Create>
  );
};

