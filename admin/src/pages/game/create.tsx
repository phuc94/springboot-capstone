import { useForm, Create, useSelect } from "@refinedev/antd";

import { Form,  Input, Select } from "antd";
import ReactQuill from "react-quill";
import 'react-quill/dist/quill.snow.css';

export const CreateGame = () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  const {selectProps: platformProps} = useSelect({
    resource: 'platform',
    optionLabel: 'title',
    optionValue: 'id',
    // filters: [
    //   {
    //     field: "isOrphan",
    //     operator: "eq",
    //     value: false,
    //   },
    // ],
  })

  return (
    <Create saveButtonProps={saveButtonProps}>
      <Form {...formProps} layout="vertical" >
        <Form.Item label="Title" name="title">
          <Input />
        </Form.Item>
        <Form.Item label="Price" name="price">
          <Input />
        </Form.Item>
        <Form.Item label="Description" name="description">
          <ReactQuill theme="snow" />
        </Form.Item>
        <Form.Item label="Thumb Image" name="media">
          <Input />
        </Form.Item>
        <Form.Item label="Platform" name="platformId">
          <Select  {...platformProps} showSearch={false} />
        </Form.Item>
      </Form>
    </Create>
  );
};


